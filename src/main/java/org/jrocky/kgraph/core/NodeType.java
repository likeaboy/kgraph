package org.jrocky.kgraph.core;

import java.awt.Color;
/**
 * 节点类型
 * @author wangzhijie
 *
 */
public enum NodeType {
	//蓝色
	ENTITY("entity",new Color(0,193,222)),
	EVENT("event",Color.orange),
	DOCUMENT("document",Color.RED);
	
	public final String name;
	public final Color color;
	private NodeType(String name,Color color){
		this.name = name;
		this.color = color;
	}
	
	public static NodeType getNodeType(String name){
		if(name.equals(ENTITY.name)){
			return ENTITY;
		}else if(name.equals(EVENT.name)){
			return EVENT;
		}else if(name.equals(DOCUMENT.name)){
			return DOCUMENT;
		}
		return ENTITY;
	}
}
