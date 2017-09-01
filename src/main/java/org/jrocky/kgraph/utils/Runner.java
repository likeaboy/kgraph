package org.jrocky.kgraph.utils;
/*package com.inspur.analysis.tool.gephi.utils;

//import org.gephi.project.api.Workspace;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.NodeIterable;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.layout.plugin.AutoLayout;
import org.gephi.layout.plugin.force.StepDisplacement;
import org.gephi.layout.plugin.force.yifanHu.YifanHuLayout;
import org.gephi.layout.plugin.forceAtlas.ForceAtlasLayout;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.openide.util.Lookup;

import com.alibaba.fastjson.JSONObject;

import gephi.dev.demo.ApplicationContextWrapper;
import gephi.dev.demo.Constant;
import gephi.dev.demo.GephiFacade;
import gephi.dev.demo.module.FileImporter;
import gephi.dev.demo.module.GephiExporter;
import gephi.dev.demo.service.IKuduWrapperService;
import gephi.dev.demo.utils.MicroServiceContextUtils;

public class Runner {
	public static void main(String[] args) {
		GephiFacade gFacade = GephiFacade.getInstance();
		Workspace workspace = gFacade.getWorkspace();
		//get data from file(kudu)
		gFacade.getGImporter().doProcess(workspace);
		//generate gexf file
		gFacade.getGExporter().doProcess(workspace);
		
		// 加载spring配置文件
		ApplicationContextWrapper.getInstance().load();
//		IKuduWrapperService kuduService = MicroServiceContextUtils.getBean(IKuduWrapperService.class);
//		String objectId = "8caee5f4036288ad779fc06172323032";
		//8caee5f4036288ad779fc06172323032
//		JSONObject json1 = kuduService.getLinkNodesById(objectId);
//		System.out.println(json1.toJSONString());
		
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        Workspace workspace = pc.getCurrentWorkspace();
        
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
        
        Map<String,Node> searchNodes = new HashMap<String,Node>();
		Map<String,Edge> searchEdges = new HashMap<String,Edge>();
		
		String oneNode = new FileImporter().doImport("oneNode.json");
		String related = new FileImporter().doImport("related.json");
		JSONObject one = (JSONObject)JSONObject.parse(oneNode);
		
		Node n0 = graphModel.factory().newNode("n"+0);
		n0.setLabel(one.get("objectName").toString());
		searchNodes.put(one.getString("objectId"), n0);
		
		
		Map<String, Object> map = (Map<String, Object>)JSONObject.parse(related);
		List<JSONObject> edges  = (List<JSONObject>)map.get("linkList");
		List<JSONObject> nodes = (List<JSONObject>)map.get("linkObjectList");
		
		
        int i =0; 
		for(JSONObject node : nodes){
			i++;
			Node n = graphModel.factory().newNode("n"+i);
			System.out.println(node.get("objectName").toString());
			n.setLabel(node.get("objectName").toString());
			searchNodes.put(node.getString("objectId"), n);
		}
		int j =0;
		for(JSONObject edge : edges){
			j++;
			Node sourceNode = searchNodes.get(edge.get("parentId"));
			Node targetNode = searchNodes.get(edge.get("childrenId"));
			Edge e = graphModel.factory().newEdge(sourceNode, targetNode, 2, true);
			searchEdges.put(edge.getString("linkId"), e);
		}
		
		DirectedGraph directedGraph = graphModel.getDirectedGraph();
		
		for(String key : searchNodes.keySet()){
			directedGraph.addNode(searchNodes.get(key));
		}
		for(String key : searchEdges.keySet()){
			directedGraph.addEdge(searchEdges.get(key));
		}
		
		YifanHuLayout layout = new YifanHuLayout(null, new StepDisplacement(1f));
		layout.setGraphModel(graphModel);
		layout.resetPropertiesValues();
		layout.setOptimalDistance(200f);
		layout.initAlgo();
		  
		for (int n = 0; n < 200 && layout.canAlgo(); n++) {
		    layout.goAlgo();
		}
		layout.endAlgo();
		NodeIterable it = directedGraph.getNodes();
		Iterator<Node> ite = it.iterator();
		while(ite.hasNext()){
			Node nn = ite.next();
			System.out.println(nn.x());
			System.out.println(nn.y());
		}
		
		
		 //Layout for 1 minute
//        AutoLayout autoLayout = new AutoLayout(1, TimeUnit.MINUTES);
//		AutoLayout autoLayout = new AutoLayout(1, TimeUnit.SECONDS);
//        autoLayout.setGraphModel(graphModel);
//        YifanHuLayout firstLayout = new YifanHuLayout(null, new StepDisplacement(1f));
//        ForceAtlasLayout secondLayout = new ForceAtlasLayout(null);
//        AutoLayout.DynamicProperty adjustBySizeProperty = AutoLayout.createDynamicProperty("forceAtlas.adjustSizes.name", Boolean.TRUE, 0.1f);//True after 10% of layout time
//        AutoLayout.DynamicProperty repulsionProperty = AutoLayout.createDynamicProperty("forceAtlas.repulsionStrength.name", new Double(500.), 0f);//500 for the complete period
//        autoLayout.addLayout(firstLayout, 0.5f);
//        autoLayout.addLayout(secondLayout, 0.5f, new AutoLayout.DynamicProperty[]{adjustBySizeProperty, repulsionProperty});
//        autoLayout.execute();
		
		

        new GephiExporter().doProcess(workspace);
        
        //Export
        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
        try {
            ec.exportFile(new File("autolayout.pdf"));
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }
		
		
		
        directedGraph.addNode(n1);
        directedGraph.addNode(n2);
        directedGraph.addEdge(e1);
        directedGraph.addEdge(e2);
        directedGraph.addEdge(e3);
		
		
//		Edge e1 = graphModel.factory().newEdge(n1, n2, 1f, true);
//        Edge e2 = graphModel.factory().newEdge(n0, n2, 2f, true);
//        Edge e3 = graphModel.factory().newEdge(n2, n0, 2f, true); 
		
		JSONObject json2 = kuduService.getNodeById(objectId);
		System.out.println(json2.toJSONString());
	}
}
*/