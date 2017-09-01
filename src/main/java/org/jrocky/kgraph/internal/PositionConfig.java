package org.jrocky.kgraph.internal;

import org.jrocky.kgraph.core.Position;

/**
 * 坐标点配置
 * @author Rocky.Wang
 *
 */
public class PositionConfig {
	final Position defInitPosition = new PositionImpl(20f, 20f);
	final static int RANDOM_POSITION_RANGE = 1000;
}
