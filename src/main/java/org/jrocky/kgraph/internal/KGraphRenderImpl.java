package org.jrocky.kgraph.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.jrocky.kgraph.KGraphContext;
import org.jrocky.kgraph.KGraphConstant;
import org.jrocky.kgraph.core.IKGraphRender;
import org.jrocky.kgraph.core.IPositionFactory;
import org.jrocky.kgraph.core.KGraphModel;
import org.jrocky.kgraph.core.KGraphOperation;
import org.jrocky.kgraph.core.Position;
import org.jrocky.kgraph.core.Prepared;
import org.jrocky.kgraph.core.PreparedEdge;
import org.jrocky.kgraph.core.PreparedNode;
import org.jrocky.kgraph.core.exception.KGraphRenderingException;
import org.jrocky.kgraph.core.exception.PolicyIsNotBindException;
import org.jrocky.kgraph.core.policy.ICheckStrategyPolicy;
import org.jrocky.kgraph.core.policy.IPreprocessPolicy;
import org.jrocky.kgraph.layout.CircularLayout;
import org.openide.util.Lookup;

/**
 * 渲染画布，从特定模型中获取元数据，在graph model中构建点集合，边集合
 * 
 * @author wangzhijie
 *
 */
public class KGraphRenderImpl implements IKGraphRender ,ICheckStrategyPolicy{

	private IPreprocessPolicy preprocessPolicy;
	private IPositionFactory positionFactory = new PositionFactoryImpl();

	public KGraphRenderImpl(IPreprocessPolicy preprocessPolicy) {
		this.preprocessPolicy = preprocessPolicy;
	}
	
	public KGraphRenderImpl() {
	}

	private void doAdd(Prepared prepared, GraphModel graphModel) {
		DirectedGraph graph = graphModel.getDirectedGraph();
		PreparedNode preparedNode = prepared.getPreparedNodes().get(0);
		if (preparedNode == null)
			throw new KGraphRenderingException(
					"KGraph Render rendering add exception , preparedNode is null");
		Node node = graphModel.factory().newNode(preparedNode.getNid());
		// 获取添加点的初始坐标位置，防止位置重叠，随着画布点的增多，性能会有所下降
//		PositionImpl position = getInitPosition(graph);
		Position position = positionFactory.getRandomPosition();
		
		node.setLabel(preparedNode.getName());
		node.setPosition(position.getX(), position.getY());
		node.setSize(KGraphConstant.NODE_BASIC_SIZE);
		node.setColor(preparedNode.getNtype().color);
		graph.addNode(node);
	}

	private boolean doExpand(Prepared prepared, GraphModel graphModel) {
		DirectedGraph graph = graphModel.getDirectedGraph();
		Node snode = graph.getNode(prepared.getTargetNodeId());
		// 检索结构
		Map<String, Node> searchNodes = new HashMap<String, Node>();
		List<Edge> edges = new ArrayList<Edge>();
		List<Node> nodes = new ArrayList<Node>();

		// create nodes
		for (PreparedNode n : prepared.getPreparedNodes()) {
			Node node = graphModel.factory().newNode(n.getNid());
			node.setLabel(n.getName());
			node.setColor(n.getNtype().color);
			nodes.add(node);
			searchNodes.put(n.getNid().toString(), node);
		}

		boolean isThrowable = true;
		for (Node n : searchNodes.values()) {
			try {
				graph.addNode(n);
				isThrowable = false;
			} catch (Exception e) {
				System.out.println(n.getId() + " node exist!");
				/**
				 * 如果该点已经在画布中存在，则需要取画布中该点放到搜索点集合，在一下步生成边时使用，
				 * 若不覆盖，由于没有执行add到graph中，所以没有storeId，在下一步生成边时会报错
				 */
				searchNodes.put(n.getId().toString(), graph.getNode(n.getId()));
				continue;
			}
		}

		// 在相关节点添加完成之后，再添加画布被展开节点
		searchNodes.put(snode.getId().toString(), snode);

		// create edges
		for (PreparedEdge e : prepared.getPreparedEdges()) {
			Node sourceNode = searchNodes.get(e.getSourceId());
			Node targetNode = searchNodes.get(e.getTargetId());
			Edge existEdge = graph.getEdge(sourceNode, targetNode);
			if (existEdge == null) {
				Edge edge = graphModel.factory().newEdge(sourceNode,
						targetNode, 0, true);
				edges.add(edge);
				isThrowable = false;
			}
		}

		if (!isThrowable) {
			try {
				graph.addAllEdges(edges);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 默认布局初始化点坐标
			CircularLayout defaultLayout = new CircularLayout();
			defaultLayout.execute(nodes, snode);

			for (Node n : graph.getNodes()) {
				System.out.println(n.getId() + " " + n.getLabel() + " ("
						+ n.x() + "," + n.y() + ")");
			}
		}

		return !isThrowable;
	}

	@Override
	public boolean rendering(KGraphModel kgModel) {
		KGraphContext context = kgModel.getContext();
		KGraphOperation operation = context.getOperation();
		// 获取预处理数据
		Prepared prepared = preprocessPolicy.handle();
		GraphModel graphModel = Lookup.getDefault()
				.lookup(GraphController.class).getGraphModel(context.get());
		boolean renderSuccess = true;

		switch (operation) {
		case ADD:
			try {
				doAdd(prepared, graphModel);
			} catch (KGraphRenderingException e) {
				e.printStackTrace();
				renderSuccess = false;
			}
			return renderSuccess;
		case EXPAND:
			return doExpand(prepared, graphModel);
		default:
			return renderSuccess;
		}

	}

	/**
	 * 获取添加点的初始化坐标
	 * 
	 * @param graph
	 * @return
	 */
	/*private PositionImpl getInitPosition(DirectedGraph graph) {
		float initNodeX = GephiConstant.defInitPosition.getX();
		float initNodeY = GephiConstant.defInitPosition.getY();
		for (Node n : graph.getNodes()) {
			if (n.x() == initNodeX && n.y() == initNodeY) {
				initNodeX = initNodeX + GephiConstant.POINT_STEP;
				initNodeY = initNodeY + GephiConstant.POINT_STEP;
			}
		}
		return new PositionImpl(initNodeX, initNodeY);
	}*/

	@Override
	public void doCheck() {
		if(preprocessPolicy == null)
			throw new PolicyIsNotBindException("preprocess policy is not bind!");
				
	}

}
