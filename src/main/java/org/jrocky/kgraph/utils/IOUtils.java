package org.jrocky.kgraph.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class IOUtils {
	
	/**
	 * 生成uuid
	 * @return
	 */
	public static String uuid(){
		UUID uuid = UUID.randomUUID();
		return Long.toHexString(uuid.getLeastSignificantBits()) + ""
				+ Long.toHexString(uuid.getMostSignificantBits());
	}
	
	public static void buffedOut(String targetFilePath,OutputStream out){
		 //读取文件  
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
		try {
			
			InputStream in = new FileInputStream(targetFilePath);
			bis = new BufferedInputStream(in);
			bos = new BufferedOutputStream(out);
	          byte[] array = new byte[1024*1024];
			 
		        //写文件  
		        int b;  
		        while((b=bis.read(array))!= -1)  
		        {  
		        	bos.write(array, 0, b);
		        }  
		          
		        if(bis != null){
		        	bis.close();
		        	bis = null;
		        }
		        if(bos != null){
		        	bos.close();
		        	bos = null;
		        }
		        if(in != null){
		        	 in.close(); 
		        	 in = null;
		        }
		        if(out != null){
		        	out.close();
		        	out = null;
		        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	/**
	 * 
	 * @param targetFilePath
	 * @param out
	 */
	public static void out(String targetFilePath,OutputStream out){
		try {
			InputStream in = new FileInputStream(targetFilePath);
		        //写文件  
		        int b;  
		        while((b=in.read())!= -1)  
		        {  
		            out.write(b);  
		        }  
		          
		        in.close();  
		        out.close(); 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
