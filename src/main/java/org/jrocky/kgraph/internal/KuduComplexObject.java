package org.jrocky.kgraph.internal;

import java.util.ArrayList;
import java.util.List;

public class KuduComplexObject {
	private List<KuduLink> linkList = new ArrayList<KuduLink>();
	private List<KuduNode> linkObjectList = new ArrayList<KuduNode>();
	public List<KuduLink> getLinkList() {
		return linkList;
	}
	public void setLinkList(List<KuduLink> linkList) {
		this.linkList = linkList;
	}
	public List<KuduNode> getLinkObjectList() {
		return linkObjectList;
	}
	public void setLinkObjectList(List<KuduNode> linkObjectList) {
		this.linkObjectList = linkObjectList;
	}
}
