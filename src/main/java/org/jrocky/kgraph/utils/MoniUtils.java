package org.jrocky.kgraph.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;

import com.alibaba.fastjson.JSONObject;

public class MoniUtils {

	public static void moni(GraphModel gm) {
		Map<String, Node> searchNodes = new HashMap<String, Node>();
		Map<String, Edge> searchEdges = new HashMap<String, Edge>();

		String oneNode = new FileImporter().doImport("oneNode.json");
		String related = new FileImporter().doImport("related.json");
		JSONObject one = (JSONObject) JSONObject.parse(oneNode);
	        
		Node n0 = gm.factory().newNode(one.getString("objectId"));
		n0.setLabel(one.get("objectName").toString());
		searchNodes.put(one.getString("objectId"), n0);

		Map<String, Object> map = (Map<String, Object>) JSONObject
				.parse(related);
		List<JSONObject> edges = (List<JSONObject>) map.get("linkList");
		List<JSONObject> nodes = (List<JSONObject>) map.get("linkObjectList");

		for (JSONObject node : nodes) {
			Node n = gm.factory().newNode(node.getString("objectId"));
			System.out.println(node.get("objectName").toString());
			n.setLabel(node.get("objectName").toString());
			searchNodes.put(node.getString("objectId"), n);
		}
		int j = 0;
		for (JSONObject edge : edges) {
			j++;
			Node sourceNode = searchNodes.get(edge.get("parentId"));
			Node targetNode = searchNodes.get(edge.get("childrenId"));
			Edge e = gm.factory().newEdge(sourceNode, targetNode, 2, true);
			searchEdges.put(edge.getString("linkId"), e);
		}
		
		DirectedGraph directedGraph = gm.getDirectedGraph();
		
		for(String key : searchNodes.keySet()){
			directedGraph.addNode(searchNodes.get(key));
		}
		for(String key : searchEdges.keySet()){
			directedGraph.addEdge(searchEdges.get(key));
		}
	}
}
