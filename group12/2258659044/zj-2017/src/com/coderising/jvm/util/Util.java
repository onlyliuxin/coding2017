package com.coderising.jvm.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Util {
	
	public static int byteToInt(byte[] codes){
    	String s1 = byteToHexString(codes);
    	return Integer.valueOf(s1, 16).intValue();
    }
       
	public static String byteToHexString(byte[] codes ){
		StringBuffer buffer = new StringBuffer();
		for(int i=0;i<codes.length;i++){
			byte b = codes[i];
			int value = b & 0xFF;
			String strHex = Integer.toHexString(value);
			if(strHex.length()< 2){
				strHex = "0" + strHex;
			}		
			buffer.append(strHex);
		}
		return buffer.toString();
	}
	
	/**
	 * 根据类完整包名与classPath获取类class文件
	 * @return
	 */
	public static File getClzFile(List<String> clzPaths ,String className){
		
		File clazFile = null;
		//将com.zj.className 转化为 com\\zj\\className.class;
		if(className!=null){
			className = className.replace(".", "\\")+".class";
		}
		//寻找文件所在目录
		for (String clzPath : clzPaths) {
			clazFile = new File(clzPath+"\\"+className);
			if(clazFile.exists()){
				break;
			}
		}
		return clazFile;
	}
	
	/**
	 * 读取文件并返回该文件的字节数组
	 * @param clzFile
	 * @return
	 */
	public static byte[] readClz(File file){
		
		byte[] data = null;
		InputStream is = null;
		ByteArrayOutputStream baos = null;
		try {			
			is = new FileInputStream(file);	
			if(is !=null){
				baos = new ByteArrayOutputStream(); 
			    byte[] buffer = new byte[1024]; 
			    int length = -1; 
			    while ((length = is.read(buffer)) != -1) { 
			        baos.write(buffer, 0, length); 
			    } 
			    baos.flush();
			    data = baos.toByteArray(); 		
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(baos!=null){
					baos.close();
				}
				if(is!=null){
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
	
}
