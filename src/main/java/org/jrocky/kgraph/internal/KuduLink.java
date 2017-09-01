package org.jrocky.kgraph.internal;

public class KuduLink {
	private String linkId;
	private String linkName;
	private String linkUri;
	private String parentId;
	private String parentPropertyId;
	private String parentProperty;
	private String childrenId;
	private String childrenPropertyId;
	private String childrenProperty;
	private String note;
	private String createTime;
	private String creator;
	private String editTime;
	private String editor;
	private int scn;
	public String getLinkId() {
		return linkId;
	}
	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkUri() {
		return linkUri;
	}
	public void setLinkUri(String linkUri) {
		this.linkUri = linkUri;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentPropertyId() {
		return parentPropertyId;
	}
	public void setParentPropertyId(String parentPropertyId) {
		this.parentPropertyId = parentPropertyId;
	}
	public String getParentProperty() {
		return parentProperty;
	}
	public void setParentProperty(String parentProperty) {
		this.parentProperty = parentProperty;
	}
	public String getChildrenId() {
		return childrenId;
	}
	public void setChildrenId(String childrenId) {
		this.childrenId = childrenId;
	}
	public String getChildrenPropertyId() {
		return childrenPropertyId;
	}
	public void setChildrenPropertyId(String childrenPropertyId) {
		this.childrenPropertyId = childrenPropertyId;
	}
	public String getChildrenProperty() {
		return childrenProperty;
	}
	public void setChildrenProperty(String childrenProperty) {
		this.childrenProperty = childrenProperty;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getEditTime() {
		return editTime;
	}
	public void setEditTime(String editTime) {
		this.editTime = editTime;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public int getScn() {
		return scn;
	}
	public void setScn(int scn) {
		this.scn = scn;
	}
}
