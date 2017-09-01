package org.jrocky.kgraph.cache;

import java.util.concurrent.ConcurrentHashMap;

import org.jrocky.kgraph.KGraphConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * GEXF布局存储全局表
 * @author Rocky.Wang
 *
 */
public class KGraphCache {
	
	private static Logger logger = LoggerFactory.getLogger(KGraphCache.class);
	
	private boolean isCache = false;
	
	/**
	 * K:sso_token用户登录凭证
	 * V:gexf_file_path Gexf文件存储在服务器上的地址
	 */
	private ConcurrentHashMap<String, String> layoutGexf = new ConcurrentHashMap();
	
	private KGraphCache(){}
	
	private static volatile KGraphCache instance = new KGraphCache();
	
	public static KGraphCache getInstance(){
		return instance;
	}
	
	public void put(String key,String filename){
		if(filename.startsWith(KGraphConstant.GEXF_CACHED_PATH))
			layoutGexf.put(key, filename);
		else
			layoutGexf.put(key, KGraphConstant.GEXF_CACHED_PATH + filename);
	}
	
	public String get(String key){
		return layoutGexf.get(key);
	}
	
	public void remove(String key){
		layoutGexf.remove(key);
	}
	
}
