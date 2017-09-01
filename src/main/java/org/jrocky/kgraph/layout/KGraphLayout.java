package org.jrocky.kgraph.layout;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.openide.util.Lookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KGraphLayout implements IKGraphLayout{
private static Logger logger = LoggerFactory.getLogger(KGraphLayout.class);
	
	private KGraphLayout(){}
	
	private static volatile KGraphLayout instance = new KGraphLayout();
	
	public static KGraphLayout getInstance(){
		return instance;
	}
	
	public boolean hyfLayout(){
		//See if graph is well imported
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
        DirectedGraph graph = graphModel.getDirectedGraph();
        System.out.println("Nodes: " + graph.getNodeCount());
        System.out.println("Edges: " + graph.getEdgeCount());
        
        if(graph.getNodeCount() == 0 && 
        		graph.getEdgeCount() == 0){
        	logger.info("container has no nodes ,no edges");
        	return false;
        }
        
        YifanHuLayout layout = new YifanHuLayout(null, new StepDisplacement(30f));
        layout.setGraphModel(graphModel);
        layout.resetPropertiesValues();
        layout.setOptimalDistance(600f);

        layout.initAlgo();
//        layout.goAlgo();
        for (int i = 0; i < 20 && layout.canAlgo(); i++) {
            layout.goAlgo();
        }
        layout.endAlgo();
        
        return true;
	}
}
