package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		if(null == className || "".equals(className)){
			return null;
		}
		className = className.replace(".", File.separator)+".class";
		Iterator<String> it = clzPaths.iterator();
		byte[] bytes = null;
		while(it.hasNext() && bytes == null){
			String filePath = it.next()+File.separator+className;
			bytes = getClassFile(filePath);
		}
		return bytes;	
		
	}
	
	
	private byte[] getClassFile(String filePath) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			InputStream is = new FileInputStream(new File(filePath));
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len=is.read(buffer)) > 0){
				bos.write(buffer,0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
	}


	public void addClassPath(String path) {
		if(null != path && !"".equals(path)){
			clzPaths.add(path);
		}
	}
	
	
	
	public String getClassPath(){
		StringBuilder sb = new StringBuilder();
		Iterator<String> it = clzPaths.iterator();
		while(it.hasNext()){
			if(sb.length() > 0){
				sb.append(";");
			}
			sb.append(it.next());
		}
		return sb.toString();
	}

	

	

}
