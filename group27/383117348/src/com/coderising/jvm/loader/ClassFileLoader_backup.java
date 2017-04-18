package com.coderising.jvm.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader_backup {

	private List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className) {
		className = className.replace(".", "\\");
		File file = null;
		for(String classPath : clzPaths){
			file = new File(classPath + "\\" + className + ".class");
			if(file.exists()){
				break;
			}
		}
		if(!file.exists()){
			try {
				throw new ClassNotFoundException();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) file.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public void addClassPath(String path) {
		if (path != null && path.length() > 0) {
			if (!clzPaths.contains(path)) {
				clzPaths.add(path);
			}
		}
	}

	public String getClassPath() {
		String paths = "";
		for (String s : clzPaths) {
			paths += s + ";";
		}
		paths = paths.substring(0, paths.length() - 1);
		return paths;
	}

}
