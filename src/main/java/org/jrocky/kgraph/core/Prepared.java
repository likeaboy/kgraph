package org.jrocky.kgraph.core;

import java.util.List;

public class Prepared {

	private List<PreparedNode> preparedNodes;
	private List<PreparedEdge> preparedEdges;
	private String targetNodeId;
	
	public Prepared(List<PreparedNode> preparedNodes,List<PreparedEdge> preparedEdges,String targetNodeId){
		this.preparedNodes = preparedNodes;
		this.preparedEdges = preparedEdges;
		this.targetNodeId = targetNodeId;
	}

	public List<PreparedNode> getPreparedNodes() {
		return preparedNodes;
	}

	public void setPreparedNodes(List<PreparedNode> preparedNodes) {
		this.preparedNodes = preparedNodes;
	}

	public List<PreparedEdge> getPreparedEdges() {
		return preparedEdges;
	}

	public void setPreparedEdges(List<PreparedEdge> preparedEdges) {
		this.preparedEdges = preparedEdges;
	}

	public String getTargetNodeId() {
		return targetNodeId;
	}

	public void setTargetNodeId(String targetNodeId) {
		this.targetNodeId = targetNodeId;
	}
}
