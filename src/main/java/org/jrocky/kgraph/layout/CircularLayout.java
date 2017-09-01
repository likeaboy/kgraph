package org.jrocky.kgraph.layout;

import java.util.List;

import org.gephi.graph.api.Node;

/**
 * 
 * @author Rocky.Wang
 *
 */
public class CircularLayout {
	
	public void execute(List<Node> nodes,Node centerNode){
		int count = nodes.size();
//		int centerX = 400;//圆心坐标  
//		int centerY = 300;  
		float centerX = centerNode.x();
		float centerY = centerNode.y();
		int radius = 200;//半径  
		//count: 节点数目              
		for (int i= 0; i<count; i++)  
		{  
		    float x = centerX+ (float)(radius * Math.cos(Math.PI * 2 / count * i));  
		    float y = centerY+ (float)(radius * Math.sin(Math.PI * 2 / count * i));
		    nodes.get(i).setPosition(x, y);
		   /* node[i].setX(x);  
		    node[i].setY(y);  */
		}  
	}
}
