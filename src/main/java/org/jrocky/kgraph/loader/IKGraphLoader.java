package org.jrocky.kgraph.loader;

import org.gephi.io.importer.api.Container;
import org.jrocky.kgraph.KGraphContext;
/**
 * Gephi Loader 
 * 导入数据到Gephi内存模型
 * @author Rocky.Wang
 *
 */
public interface IKGraphLoader {
	Container load(KGraphContext context);
}
