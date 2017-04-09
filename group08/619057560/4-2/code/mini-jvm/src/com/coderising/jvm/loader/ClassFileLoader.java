package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		for (String path: clzPaths) {
			String fileName = path + '/' + className.replace('.', '/') + ".class";
			System.out.println(fileName);
			File file = new File(fileName);
			if (file.exists()) {
				return loadClassFile(fileName);
			}
		}
		return null;	
		
		
	}
	
	private byte[] loadClassFile(String clzFileName) {
		File file = new File(clzFileName);
		int len;
		int bufferLen = 100;
		byte[] buffer = new byte[bufferLen];
		FileInputStream fis = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			fis = new FileInputStream(file);
			while ((len = fis.read(buffer, 0, bufferLen)) >= 0) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	public String getClassPath_V1(){
		
		return  null;
	}
	
	public String getClassPath(){
		StringBuilder sb = new StringBuilder();
		for (String path: clzPaths) {
			sb.append(path).append(";");
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	

	

}
