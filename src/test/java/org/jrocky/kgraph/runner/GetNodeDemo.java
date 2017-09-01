package org.jrocky.kgraph.runner;

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
import org.jrocky.kgraph.internal.KuduPreprocessPolicy;
import org.jrocky.kgraph.layout.KGraphLayout;
import org.jrocky.kgraph.loader.KGraphLoader;
import org.jrocky.kgraph.uploader.GexfUploader;
import org.openide.util.Lookup;

public class GetNodeDemo {

	
	public void moniAjaxGetNode(){
		String objectId = "O190230";
		//gexf文件
		String data = "";
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
		Map<String, Object> node = new HashMap<String,Object>();
		
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
		//输出gexf文件流
//		printGexfFileStream(true,response,context);
	}
}
