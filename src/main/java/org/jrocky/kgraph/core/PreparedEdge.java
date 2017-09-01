package org.jrocky.kgraph.core;
/**
 * 预处理边
 * @author wangzhijie
 *
 */
public class PreparedEdge {

	private String sourceId;
	private String targetId;
	private String linkName;
	public PreparedEdge(String sourceId, String targetId,String linkName) {
		super();
		this.sourceId = sourceId;
		this.targetId = targetId;
		this.linkName = linkName;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
}
