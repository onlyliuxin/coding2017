package com.coderising.jvm.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		
		File clzFile = ClassFileLoaderUtil.getClzFile(clzPaths,className);
        		
		return ClassFileLoaderUtil.readClz(clzFile);	
				
	}
		
	public void addClassPath(String path) {
		
		this.clzPaths.add(path);
	}
			
	public String getClassPath(){
		
		StringBuffer buff = new StringBuffer();
		for (String str : clzPaths) {
			buff.append(str+";");
		}		
		return buff.substring(0, buff.length()-1);
	}

}
