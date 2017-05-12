package com.loader;

import java.io.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<>();
	
	public byte[] readBinaryCode(String className) throws ClassNotFoundException {

		String fileName = className.replace('.',File.separatorChar)+".class";
		InputStream is = null;
		try{
			is = new FileInputStream(fileName);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length = 0;
			while((length = is.read(buffer)) != -1){
				baos.write(buffer,0,length);
			}
			return baos.toByteArray();
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			if(is!=null){
				try{
					is.close();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		return null;
	}




	public void addClassPath(String path) {

		StringBuilder str = new StringBuilder(path);

	}
	
	
	
	public String getClassPath(){

		String path = Thread.currentThread().getContextClassLoader().getResource("/").getPath();

		return path;
	}

	

	

}
