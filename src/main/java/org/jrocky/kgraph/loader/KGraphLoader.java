package org.jrocky.kgraph.loader;

import java.io.File;

import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.DefaultProcessor;
import org.gephi.project.api.Project;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.jrocky.kgraph.KGraphContext;
import org.jrocky.kgraph.core.security.GephiCheck;
import org.jrocky.kgraph.core.security.IGephiCheck;
import org.openide.util.Lookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Gexf导入模块
 * @author wangzhijie
 *
 */
public class KGraphLoader implements IKGraphLoader{
	
	private static Logger logger = LoggerFactory.getLogger(KGraphLoader.class);
	
	private KGraphLoader(){}
	
	private static volatile KGraphLoader instance = new KGraphLoader();
	private IGephiCheck gCheck = new GephiCheck();
	
	public static KGraphLoader getInstance(){
		return instance;
	}

	public Container load(KGraphContext context){
		 //Init a project - and therefore a workspace
//	    ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
//	    pc.newProject();
//	    Workspace workspace = pc.getCurrentWorkspace();
		
	    //Get controllers and models
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
	    
        //Import file
        Container container;
        try {
        	///org/gephi/toolkit/demos/lesmiserables.gml
//            File file = new File(getClass().getResource(context.getGexfAddr()).toURI());
            File file = new File(context.getGexfAddr());
            container = importController.importFile(file);
            container.getLoader().setEdgeDefault(EdgeDirectionDefault.DIRECTED);   //Force DIRECTED
            container.getLoader().setAllowAutoNode(false);  //Don't create missing nodes
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        
        if(!gCheck.check()){
        	//clear current workspace
        	ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
//    	    pc.newProject();
//        	pc.closeCurrentProject();
        	pc.closeCurrentWorkspace();
//        	pc.newWorkspace(project);
        	Project project = pc.getCurrentProject();
        	if(project == null){
        		pc.newProject();
        		context.rebind(pc.getCurrentWorkspace());
        	}else{
        		context.rebind(pc.getCurrentWorkspace());
        	}
        }
        
        //Append imported data to GraphAPI
        try{
        	importController.process(container, new DefaultProcessor(), context.get());
        }catch(Exception e){
        	e.printStackTrace();
        }
	    
        return container;
	}
}
