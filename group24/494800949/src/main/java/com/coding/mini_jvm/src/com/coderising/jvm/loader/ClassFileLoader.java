package com.coding.mini_jvm.src.com.coderising.jvm.loader;

import com.coding.mini_jvm.src.com.coderising.jvm.clz.ClassFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private static final String CLASS_FILE_SUFFIX = ".class";
	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		String classPath = getClassPath();
		String[] paths = classPath.split(File.pathSeparator);
		className = className.replace('.', File.separatorChar) ;
		for (String path : paths) {
			String clzFilename = path + File.separator + className + CLASS_FILE_SUFFIX;
			byte[] data = loadClassFile(clzFilename);
			if (data != null) {
				return data;
			}
		}
		return null;
	}

	private byte[] loadClassFile(String clzFileName) {
		File file = new File(clzFileName);
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			byte[] data = new byte[bis.available()];
			bis.read(data);
			return data;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void addClassPath(String path) {
		if (this.clzPaths.contains(path)) {
			return;
		}
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


	public ClassFile loadClass(String className) {
		byte[] data = readBinaryCode(className);
		ClassFileParser classFileParser = new ClassFileParser();
		return classFileParser.parse(data);
	}
}
