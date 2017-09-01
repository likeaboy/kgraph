package org.jrocky.kgraph.internal;

import org.jrocky.kgraph.core.IPositionFactory;
import org.jrocky.kgraph.core.Position;

/**
 * 点坐标工厂
 * @author wangzhijie
 *
 */
public class PositionFactoryImpl implements IPositionFactory{
	
	@Override
	public Position getDefaultPosition() {
		return newPosition(20f,20f);
	}

	@Override
	public Position getRandomPosition() {
		float x = (float)Math.random() * PositionConfig.RANDOM_POSITION_RANGE;
		float y = (float)Math.random() * PositionConfig.RANDOM_POSITION_RANGE;
		return new PositionImpl(x, y);
	}
	
	private Position newPosition(float x,float y){
		return new PositionImpl(x, y);
	}
	
}
