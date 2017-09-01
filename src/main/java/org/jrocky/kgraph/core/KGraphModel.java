package org.jrocky.kgraph.core;

import org.jrocky.kgraph.KGraphContext;

/**
 * 知识图谱渲染模型
 * @author wangzhijie
 *
 */
public class KGraphModel {

	private KGraphContext context;
	
	public KGraphModel(KGraphContext context){
		this.context = context;
	}

	public KGraphContext getContext() {
		return context;
	}

	public void setContext(KGraphContext context) {
		this.context = context;
	}
}
