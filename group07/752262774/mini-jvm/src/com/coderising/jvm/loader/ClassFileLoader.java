package com.coderising.jvm.loader;

import com.sun.deploy.util.StringUtils;
import sun.misc.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();

	static final int BUFFER_SIZE = 1024;

	public byte[] readBinaryCode(String className) {
		String path = getClassPath() + className.replace(".", "\\") + ".class";
		ByteArrayOutputStream bArrout = new ByteArrayOutputStream();

		try {
			InputStream inputStream = new FileInputStream(new File(path));
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				bArrout.write(buffer, 0, len);
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		byte[] bytes = bArrout.toByteArray();

		try {
			bArrout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bytes;
	}

	public byte[] readBinaryCode2(String className) {
		className = className.replace('.', File.separatorChar) +".class";

		for(String path : this.clzPaths) {
			String clzFileName = path + File.separatorChar + className;
			byte[] codes = loadClassFile(clzFileName);
			if (codes != null) {
				return codes;
			}
		}

		return null;
	}

	private byte[] loadClassFile(String clzFileName) {
		File f = new File(clzFileName);
		try {
		    FileInputStream fi = new FileInputStream(f);
			return IOUtils.readFully(fi, fi.available() , true);
			//return IOUtils.toByteArray(new FileInputStream(f));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void addClassPath(String path) {
		if(this.clzPaths.contains(path)) {
			return;
		}
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuilder paths = new StringBuilder();
		for (String path: clzPaths) {
			paths.append(path);
			paths.append(";");
		}
		paths.deleteCharAt(paths.length()-1);
		return paths.toString();
	}

	public String getClassPath2() {
		return StringUtils.join(this.clzPaths, ";");
	}

	

	

}
