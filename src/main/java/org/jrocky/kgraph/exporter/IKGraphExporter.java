package org.jrocky.kgraph.exporter;

import org.jrocky.kgraph.KGraphContext;

/**
 * KGraph Exporter
 * KGraph导出模块
 * @author Rocky.Wang
 *
 */
public interface IKGraphExporter {
	String export(KGraphContext context);
}
