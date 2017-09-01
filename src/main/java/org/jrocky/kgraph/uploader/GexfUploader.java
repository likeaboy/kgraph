package org.jrocky.kgraph.uploader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import org.jrocky.kgraph.KGraphConstant;
import org.jrocky.kgraph.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Gexf文件上传模块
 * @author wangzhijie
 *
 */
public class GexfUploader {
	
	private static Logger logger = LoggerFactory.getLogger(GexfUploader.class);

	private GexfUploader(){}
	
	private static volatile GexfUploader instance = new GexfUploader();
	
	public static GexfUploader getInstance(){
		return instance;
	}
	
	public String execute(String data){
		String fileSuffix = ".gexf";
		String savedFileName = IOUtils.uuid().concat(fileSuffix);
		String savedDir = KGraphConstant.GEXF_CACHED_PATH;
		String absolutePath = savedDir + File.separatorChar + savedFileName;
		try  
	    {      
	      // 创建文件对象  
	      File fileText = new File(savedDir,savedFileName);  
	      // 向文件写入对象写入信息  
	      FileWriter fileWriter = new FileWriter(fileText);  
	  
	      // 写文件        
	      fileWriter.write(data);  
	      // 关闭  
	      fileWriter.close();  
	    }  
	    catch (IOException e)  
	    {  
	      //  
	      e.printStackTrace();  
	    }  
		return absolutePath;
	}
	
	public String execute(Map<String, MultipartFile> files,Iterator<String> fileNames,String remoteAddr){
		String absolutePath = "";
		try {
//            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//            Map<String, MultipartFile> files = multipartRequest.getFileMap();
//            Iterator<String> fileNames = multipartRequest.getFileNames();
            while (fileNames.hasNext()) {
                InputStream is = null;
                try {
                    String oneFileName = fileNames.next();
                    CommonsMultipartFile file = (CommonsMultipartFile) files
                            .get(oneFileName);
                    String originalName = file.getFileItem().getName();
					int pointIndex = originalName.indexOf("."); // 点号的位置
					String fileSuffix = originalName.substring(pointIndex); // 截取文件后缀
					String savedFileName = IOUtils.uuid().concat(fileSuffix);
//					savedDir = /*request.getSession().getServletContext().getRealPath("/")*/ TaskConstant.getStructedPath(); // 获取服务器指定文件存取路径
					String savedDir = KGraphConstant.GEXF_CACHED_PATH;
					absolutePath = savedDir + File.separatorChar + savedFileName;
					
                    try {
                        if (!(new File(savedDir).isDirectory())) {
                            new File(savedDir).mkdir();//创建目录
                        }
                    } catch (SecurityException e) {
                        logger.error("Client: {} save plugin file error with", remoteAddr,
                                e.fillInStackTrace().toString());
                        e.printStackTrace();
                    }
                    File savedFile = new File(savedDir, savedFileName);
                    boolean isCreateSuccess = savedFile.createNewFile();
                    if (isCreateSuccess) {
                        file.transferTo(savedFile);  //转存文件
                    }
                } catch (Exception e) {
                    logger.error("Client: {} upload file error :{}", remoteAddr, e.fillInStackTrace().toString());
                    e.printStackTrace();
                } finally {
                    if (is != null) {
                        is.close();
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Client: {} upload file error :{}", remoteAddr, e.fillInStackTrace().toString());
            e.printStackTrace();
        }
		
		return absolutePath;
	}
}
