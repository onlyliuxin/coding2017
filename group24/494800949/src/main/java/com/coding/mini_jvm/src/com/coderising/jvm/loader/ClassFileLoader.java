package com.coding.mini_jvm.src.com.coderising.jvm.loader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private static final String CLASS_FILE_SUFFIX = ".class";
	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		String classPath = getClassPath();
		String[] paths = classPath.split(File.pathSeparator);
		className = className.replaceAll("\\.", "\\"+File.separator) ;
		for (String path : paths) {
			String filename = path + File.separator + className + CLASS_FILE_SUFFIX;
			File file = new File(filename);
			if (file.exists()) {
				try {
					FileInputStream fis = new FileInputStream(file);
					BufferedInputStream bis = new BufferedInputStream(fis);
					byte[] data = new byte[bis.available()];
					bis.read(data);
					return data;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuffer sb = new StringBuffer();
		for (String path : clzPaths) {
			sb.append(path);
			sb.append(";");
		}
		String path = sb.toString();
		return path.substring(0, path.lastIndexOf(";"));
	}

	

	

}
