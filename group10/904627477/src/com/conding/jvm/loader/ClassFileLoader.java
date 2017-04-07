package com.conding.jvm.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.coding.util.IOUtils;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className){
		String filePath = getFilePath(className);
		if(filePath==null){
			return null;
		}
		byte[] result = IOUtils.readFile(filePath);
		return result;	
	}	
	
	public String getFilePath(String className) {
		String filePath = null;
		String relativePath = className.replace('.', '/')+".class";
		for (String str : clzPaths) {
			String tempPath = str + "/" + relativePath;
			File file = new File(tempPath);
			if(file.exists()){
				filePath = tempPath;
				break;
			}
		}
		return filePath;
	}

	public void addClassPath(String path) {
		if(path==null||"".equals(path)){
			return;
		}
		if(clzPaths.indexOf(path)!=-1){
			return ;
		}
		clzPaths.add(path);
	}
	
	public String getClassPath(){
		StringBuffer sb = new StringBuffer();
		for (String clzPath : clzPaths) {
			sb.append(clzPath+";");
		}
		return sb.length()==0?"":sb.substring(0, sb.length()-1);
	}

}
