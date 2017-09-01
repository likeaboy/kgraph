package org.jrocky.kgraph.core;
/**
 * 度计算策略
 * @author wangzhijie
 *
 */
public interface IDegreeStrategy {

	float getStrategy(int degree);
}
