package org.jrocky.kgraph.core;
/**
 * 预处理节点
 * @author wangzhijie
 *
 */
public class PreparedNode {

	private String nid;
	private String name;
	private NodeType ntype;
	private String icon;
	
	public PreparedNode(String nid, String name, NodeType ntype, String icon) {
		super();
		this.nid = nid;
		this.name = name;
		this.ntype = ntype;
		this.icon = icon;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public NodeType getNtype() {
		return ntype;
	}
	public void setNtype(NodeType ntype) {
		this.ntype = ntype;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
