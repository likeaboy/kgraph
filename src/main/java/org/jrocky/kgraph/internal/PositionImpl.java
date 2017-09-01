package org.jrocky.kgraph.internal;

import org.jrocky.kgraph.core.Position;

/**
 * 点坐标位置
 * 
 * @author wangzhijie
 *
 */
class PositionImpl implements Position{
	private float x;
	private float y;
	
	public PositionImpl(float x,float y){
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
