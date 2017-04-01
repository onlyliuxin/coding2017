package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) { 
		String classDirName = className.replace('.', '\\')+".class";
		String clzpath = getClassPath()+"\\"+ classDirName;
		try {
			FileInputStream clz = new FileInputStream(clzpath);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			int flag = 0;
			while((flag = clz.read())!=-1){
				baos.write(flag);
			}
			clz.close();
			return baos.toByteArray();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void addClassPath(String path) {
		File file = new File(path);
		if(file.exists()){
			clzPaths.add(path);
		} else {
			throw new IllegalArgumentException("路径："+path+"不存在");
		}
	}
	

	public String getClassPath(){
		if(clzPaths.isEmpty()){
			return " ";
		}
		
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < clzPaths.size(); i++) {
			buf.append(clzPaths.get(i));
			if(i != (clzPaths.size() - 1)){
				buf.append(";");
			}
		}
		return buf.toString();
	}

}