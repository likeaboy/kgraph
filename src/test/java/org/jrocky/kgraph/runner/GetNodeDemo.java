package org.jrocky.kgraph.runner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.gephi.io.importer.api.Container;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.jrocky.kgraph.KGraphContext;
import org.jrocky.kgraph.cache.KGraphCache;
import org.jrocky.kgraph.core.IKGraphRender;
import org.jrocky.kgraph.core.KGraphModel;
import org.jrocky.kgraph.core.KGraphOperation;
import org.jrocky.kgraph.exporter.KGraphExporter;
import org.jrocky.kgraph.internal.KuduNode;
import org.jrocky.kgraph.internal.KuduPreprocessPolicy;
import org.jrocky.kgraph.layout.KGraphLayout;
import org.jrocky.kgraph.loader.KGraphLoader;
import org.jrocky.kgraph.uploader.GexfUploader;
import org.jrocky.kgraph.utils.FileImporter;
import org.openide.util.Lookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class GetNodeDemo {

	private static Logger logger = LoggerFactory.getLogger(GetNodeDemo.class);
	
	public static void main(String[] args) {
		new GetNodeDemo().moniAjaxGetNode();
	}
	/**
	 * 获取点数据
	 * @return
	 */
	public Object getNodeData(String objectId){
		FileImporter importer = new FileImporter();
		String oneNode = importer.doImport("oneNode.json");
		KuduNode kOjbect = JSON.parseObject(oneNode, KuduNode.class);
		return kOjbect;
	}
	
	public void moniAjaxGetNode(){
		//对应oneNode文件json对象的objectId
		String objectId = "eedacd170d872f550923dc4e8d819626";
		//gexf文件
		FileImporter importer = new FileImporter();
		String data = importer.doImport("originMap.gexf");
		String ssoToken = "";
		//打开Gephi工作空间
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
	    pc.newProject();
	    Workspace workspace = pc.getCurrentWorkspace();
	    //初始化Gephi上下文
	    KGraphContext context = new KGraphContext(workspace);
	    context.setOperation(KGraphOperation.ADD);
		
		//缓存策略token，目前采用sso_token，需保证每个用户唯一
//		String sso_token = "rocky";
		//持久化gexf文件数据，返回gexf文件地址
		String gexfAddr = GexfUploader.getInstance().execute(data);
		//缓存画布数据
		KGraphCache.getInstance().put(ssoToken, gexfAddr);
		context.setGexfAddr(gexfAddr);
		
		//Gephi加载gexf数据
		Container container = KGraphLoader.getInstance().load(context);
		
		//获取kudu数据
		/*Map<String, Object> node = archiveService
				.getArchiveByObjectId(objectId);*/
		
		Object node = getNodeData(objectId);
		
		//聚合
//		GephiConvert.getInstance().convert4Add(node, context);
		
		KGraphModel kgModel = new KGraphModel(context);
		Object[] objArrs = new Object[1];
		objArrs[0] = node;
		
		//渲染
		IKGraphRender render = IKGraphRender.Factory.newInstance(new KuduPreprocessPolicy(objArrs,objectId));
		
		boolean isRendering = render.rendering(kgModel);
		
		//布局
		boolean isLayout = KGraphLayout.getInstance().hyfLayout();
		
		KGraphExporter.getInstance().export(context);
		//输出gexf文件流
//		printGexfFileStream(true,response,context);
		
		pc.closeCurrentWorkspace();
	}
	
	/*private void printGexfFileStream(boolean isLayout,HttpServletResponse response,GephiContext context){
		if (isLayout) {
			String layoutGexfFile = KGraphExporter.getInstance().export(context);
			String gexfFileName = layoutGexfFile.substring(layoutGexfFile.lastIndexOf('\\')+1);
	          
	        //设置文件MIME类型  
	        response.setContentType("text/gexf");  
	        //设置Content-Disposition  
	        response.setHeader("Content-Disposition", "attachment;filename="+gexfFileName);  
	        System.out.println(layoutGexfFile);  
	        logger.info("layout gexf file path : " + layoutGexfFile);
	        try {
				IOUtils.buffedOut(layoutGexfFile, response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/
}
