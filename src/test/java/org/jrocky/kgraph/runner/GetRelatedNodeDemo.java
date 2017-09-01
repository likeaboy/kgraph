package org.jrocky.kgraph.runner;

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
import org.jrocky.kgraph.internal.GephiDegreeCounter;
import org.jrocky.kgraph.internal.KuduComplexObject;
import org.jrocky.kgraph.internal.KuduNode;
import org.jrocky.kgraph.internal.KuduPreprocessPolicy;
import org.jrocky.kgraph.layout.KGraphLayout;
import org.jrocky.kgraph.loader.KGraphLoader;
import org.jrocky.kgraph.uploader.GexfUploader;
import org.jrocky.kgraph.utils.FileImporter;
import org.openide.util.Lookup;

import com.alibaba.fastjson.JSON;

/**
 * 运行demo，展开targetNode目标节点的相关节点，输出的在gexf文件夹下
 * @author Rocky.Wang
 *
 */
public class GetRelatedNodeDemo {
	
	public static void main(String[] args) {
		new GetRelatedNodeDemo().moniAjaxGetRelatedNodes();
	}
	
	private KuduComplexObject getRelatedData(String objectId){
		FileImporter importer = new FileImporter();
		String related = importer.doImport("related.json");
		KuduComplexObject complexObject = JSON.parseObject(related, KuduComplexObject.class);
		return complexObject;
	}

	public void moniAjaxGetRelatedNodes(){
		String objectId = "eedacd170d872f550923dc4e8d819626";
		//gexf文件
		FileImporter importer = new FileImporter();
		String data = importer.doImport("oneNode.gexf");
		//打开Gephi工作空间
		ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
	    pc.newProject();
	    Workspace workspace = pc.getCurrentWorkspace();
	    //初始化Gephi上下文
	    KGraphContext context = new KGraphContext(workspace);
	    context.setOperation(KGraphOperation.EXPAND);
		
		//缓存策略token，目前采用sso_token，需保证每个用户唯一
		String sso_token = "rocky";
		//持久化gexf文件数据，返回gexf文件地址
		String gexfAddr = GexfUploader.getInstance().execute(data);
		
		String ssoToken="rocky";

		//缓存画布数据
		KGraphCache.getInstance().put(ssoToken, gexfAddr);
		context.setGexfAddr(gexfAddr);
		
		//Gephi加载gexf数据
		Container container = KGraphLoader.getInstance().load(context);
		
		//从kudu获取数据
       /* Map<String, Object> linkNodes = linkService
				.findLinkByConditionForGraph(objectId, null);*/
		KuduComplexObject compObject = getRelatedData(objectId);
		
        
        KGraphModel kgModel = new KGraphModel(context);
        Object[] objArrs = (Object[]) compObject.getLinkObjectList().toArray();
		Object[] linkArrs = (Object[]) compObject.getLinkList().toArray();
        
        //渲染
		IKGraphRender render = IKGraphRender.Factory.newInstance(new KuduPreprocessPolicy(objArrs,linkArrs,objectId));
		
        boolean isRendering = render.rendering(kgModel);
        if(!isRendering) {
        	pc.closeCurrentWorkspace();
        	System.out.println("No Edit");
        	return;
        }
        
        
        //计算度
  		GephiDegreeCounter.getInstance().execute(context);
  		
		//过滤
//		GephiFilter.getInstance().filter(context);
  		
  		//布局
		boolean isLayout = KGraphLayout.getInstance().hyfLayout();
		//输出gexf文件流
//		printGexfFileStream(isLayout,response,context);
		
		KGraphExporter.getInstance().export(context);
		
		pc.closeCurrentWorkspace();
	}
}
