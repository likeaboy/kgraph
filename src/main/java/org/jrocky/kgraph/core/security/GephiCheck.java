package org.jrocky.kgraph.core.security;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.impl.GraphStoreConfiguration;
import org.openide.util.Lookup;

public class GephiCheck implements IGephiCheck{

	@Override
	public boolean check() {
		//获取当前工作空间画布模型
		 GraphModel graphModel = Lookup.getDefault()
					.lookup(GraphController.class).getGraphModel();
		 DirectedGraph graph = graphModel.getDirectedGraph();
		 if(graph.getNodeCount() > 0 || graph.getAttributeKeys().isEmpty() || graph.getEdgeCount() > 0)
			 return false;
//         throw new IllegalStateException("The store should be empty when modifying the configuration");
		 return true;
	}

}
