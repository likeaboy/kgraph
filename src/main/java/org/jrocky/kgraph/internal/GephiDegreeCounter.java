package org.jrocky.kgraph.internal;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.jrocky.kgraph.KGraphContext;
import org.jrocky.kgraph.KGraphConstant;
import org.jrocky.kgraph.core.IDegreeStrategy;
import org.jrocky.kgraph.core.IGephiDegreeCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 度计算模块
 * @author wangzhijie
 *
 */
public class GephiDegreeCounter implements IGephiDegreeCounter,IDegreeStrategy{
	
	private static Logger logger = LoggerFactory.getLogger(GephiDegreeCounter.class);
	
	private GephiDegreeCounter(){}
	
	private static volatile GephiDegreeCounter instance = new GephiDegreeCounter();
	
	public static GephiDegreeCounter getInstance(){
		return instance;
	}

	@Override
	public void execute(KGraphContext context) {
		GraphModel graphModel = context.getGraphModel();
		DirectedGraph dGraph = graphModel.getDirectedGraph();
		//根据度计算节点大小size
		for(Node n : dGraph.getNodes()){
			int degree = dGraph.getDegree(n);
//			n.setSize(20f+degree*0.1f);
			n.setSize(getStrategy(degree));
		}
	}

	@Override
	public float getStrategy(int degree) {
		return KGraphConstant.NODE_BASIC_SIZE+degree * KGraphConstant.NODE_SIZE_RATIO;
	}

}
