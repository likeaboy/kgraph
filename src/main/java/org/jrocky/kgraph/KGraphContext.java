package org.jrocky.kgraph;

import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.project.api.Workspace;
import org.jrocky.kgraph.core.KGraphOperation;
import org.jrocky.kgraph.core.exception.KGraphContextInternalException;
import org.openide.util.Lookup;

/**
 * KGraph Context 上下文
 * @author Rocky.Wang
 *
 */
public class KGraphContext {

	private static final ThreadLocal<Workspace> workspace = new ThreadLocal<Workspace>();
	private String gexfAddr;
	private GraphModel graphModel;
	private KGraphOperation operation;
	
	public KGraphContext(Workspace ws){
		bind(ws);
	}
	
	public GraphModel getGraphModel(){
		if(this.graphModel == null)
			this.graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
		return this.graphModel;
	}
	
	public void bind(Workspace ws){
		if(workspace.get() == null)
			workspace.set(ws);
	}
	
	public void rebind(Workspace ws){
		if(workspace.get() != null)
			workspace.set(ws);
	}
	
	public Workspace get(){
		return workspace.get();
	}
	
	public void remove(){
		workspace.remove();
	}

	public String getGexfAddr() {
		return gexfAddr;
	}

	public void setGexfAddr(String gexfAddr) {
		this.gexfAddr = gexfAddr;
	}

	public KGraphOperation getOperation() {
		if(operation == null)
			throw new KGraphContextInternalException("RGraph context operation is not init!");
		return operation;
	}

	public void setOperation(KGraphOperation operation) {
		this.operation = operation;
	}
}
