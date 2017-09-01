package org.jrocky.kgraph.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jrocky.kgraph.core.Prepared;
import org.jrocky.kgraph.core.PreparedEdge;
import org.jrocky.kgraph.core.PreparedNode;
import org.jrocky.kgraph.core.exception.GlobalVariableInvalidException;
import org.jrocky.kgraph.core.policy.IPreprocessPolicy;
import org.jrocky.kgraph.core.NodeType;


public class KuduPreprocessPolicy implements IPreprocessPolicy{
	
	/**
	 * kudu返回的相关节点数据结构
	 */
	//Map<String, Object>
	private Object[] kuduNodes;
	//ObLink
	private Object[] kuduLinks;
	/**
	 * 被展开节点ID
	 */
	private String targetNodeId;
	
//	Object[] objArrs = (Object[]) linkNodes.get("linkObjectList");
//	Object[] linkArrs = (Object[]) linkNodes.get("linkList");
	
	public KuduPreprocessPolicy(Object[] kuduNodes,String targetNodeId) {
		this(kuduNodes,new Object[1],targetNodeId);
	}

	public KuduPreprocessPolicy(Object[] kuduNodes,Object[] kuduLinks,String targetNodeId) {
		this.kuduNodes = kuduNodes;
		this.kuduLinks = kuduLinks;
		this.targetNodeId = targetNodeId;
	}
	
	private void checkNullPointer(){
		if(this.kuduNodes == null)
			throw new GlobalVariableInvalidException("kuduNodes is null");
		if(this.kuduLinks == null)
			throw new GlobalVariableInvalidException("kuduLinks is null");
	}

	@Override
	public Prepared handle() {
		checkNullPointer();
		List<PreparedNode> preparedNodeList = new ArrayList<PreparedNode>();
		PreparedNode preparedNode;
		for (Object kdNode : kuduNodes) {
			if(kdNode!=null){
				KuduNode kObject = (KuduNode)kdNode;
//				Map<String, Object> objMap = (Map<String, Object>) kdNode;
				preparedNode = new PreparedNode(kObject.getObjectId(),
						kObject.getObjectName(),NodeType.getNodeType(kObject.getObjectType()),
						kObject.getObjectIcon());
				preparedNodeList.add(preparedNode);
			}
		}
		List<PreparedEdge> preparedEdgeList = new ArrayList<PreparedEdge>();
		PreparedEdge preparedEdge;
		for(Object kuduLink : kuduLinks){
			if(kuduLink != null){
				KuduLink e = (KuduLink)kuduLink;
				preparedEdge = new PreparedEdge(e.getParentId(),e.getChildrenId(),e.getLinkName());
				preparedEdgeList.add(preparedEdge);
			}
		}
		
		return new Prepared(preparedNodeList,preparedEdgeList,this.targetNodeId);
	}
}
