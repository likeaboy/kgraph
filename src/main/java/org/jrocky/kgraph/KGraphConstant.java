package org.jrocky.kgraph;

import java.io.File;

public class KGraphConstant {
	
	public static final String GEXF_FORMAT = ".gexf";
	public static final String FOLDER = "gexf";
	public static final String PROJECT_CONTEXT = System.getProperty("user.dir") + File.separator;
	public static final String GEXF_CACHED_PATH = PROJECT_CONTEXT + FOLDER + File.separator;
	
	/**
	 * 度过滤阈值
	 */
	public static final int FILTER_DEGREE_THRESHOLD = 5;
	/**
	 * 节点基本大小
	 */
	public static final float NODE_BASIC_SIZE = 4f;
	
	/**
	 * 节点大小度系数
	 */
	public static final float NODE_SIZE_RATIO = 0.2f;
	/**
	 * 默认初始化坐标点(20,20)
	 *//*
	public static final PositionImpl defInitPosition = new PositionImpl(20f,20f);*/
	/**
	 * 坐标点步长
	 */
	public static final float POINT_STEP = 40f;
	/**
	 * 任务执行方式
	 *  var color = {
	        'entity': '#00c1de',
	        'event': '#00c1de',
	        'document': '#00c1de'
	    };
	 */
}
