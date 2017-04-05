package com.coderising.jvm.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) throws Exception {
		FileInputStream in;
		byte[] result;

		in = new FileInputStream(getClassPath().split(";")[0] + className.replace(".","\\")+".class");
		result = new byte[in.available()];

		in.read(result);

		return result;
		
		
	}
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuilder sb = new StringBuilder();
		for (String s : clzPaths) {
			sb.append(s);
			sb.append(";");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	

	

}
