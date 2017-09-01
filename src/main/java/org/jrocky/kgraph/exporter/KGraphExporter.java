package org.jrocky.kgraph.exporter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.exporter.preview.PDFExporter;
import org.gephi.io.exporter.spi.CharacterExporter;
import org.gephi.io.exporter.spi.Exporter;
import org.gephi.io.exporter.spi.GraphExporter;
import org.gephi.project.api.Workspace;
import org.jrocky.kgraph.KGraphContext;
import org.jrocky.kgraph.KGraphConstant;
import org.jrocky.kgraph.utils.IOUtils;
import org.openide.util.Lookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.PageSize;

public class KGraphExporter {
	private static Logger logger = LoggerFactory.getLogger(KGraphExporter.class);
	
	private KGraphExporter(){}
	
	private static volatile KGraphExporter instance = new KGraphExporter();
	
	public static KGraphExporter getInstance(){
		return instance;
	}
	
	/*public String exportStream(Workspace workspace){
		 //Export full graph
        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
        
      //Export only visible graph
        GraphExporter exporter = (GraphExporter) ec.getExporter("gexf");     //Get GEXF exporter
        exporter.setExportVisible(true);  //Only exports the visible (filtered) graph
        exporter.setWorkspace(workspace);
        ec.e
        
        return 
	}*/
	
	public String export(KGraphContext context){
		 //Export full graph
        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
		
		//Export only visible graph
        GraphExporter exporter = (GraphExporter) ec.getExporter("gexf");     //Get GEXF exporter
        exporter.setExportVisible(true);  //Only exports the visible (filtered) graph
        exporter.setWorkspace(context.get());
        //导出路径
        String exportPath = KGraphConstant.GEXF_CACHED_PATH+IOUtils.uuid()+KGraphConstant.GEXF_FORMAT;
        try {
            ec.exportFile(new File(exportPath), exporter);
        } catch (IOException ex) {
            ex.printStackTrace();
            return exportPath;
        }
        
        return exportPath;
	}
}
