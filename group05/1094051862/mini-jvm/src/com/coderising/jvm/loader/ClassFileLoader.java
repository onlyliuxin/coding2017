package com.coderising.jvm.loader;

import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		
		return null;	
	}
	
	
	public void addClassPath(String path) {
		
		if (path == null) {
			return;
		}
		
		clzPaths.add(path);
		
	}
	
	public String getClassPath(){
		
		if (clzPaths.size() == 0) {
			return "";
		}
		
		StringBuffer buffer = new StringBuffer("");
		
		for (String str : clzPaths) {
			buffer.append(str);
			buffer.append(";");
		}
		
		return buffer.substring(0, buffer.length()-1);//去除最后一个分号
		
	}


}
