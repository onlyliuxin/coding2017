package com.coderising.jvm.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		/**
		 * class文件存储位置
		 */
		String location = clzPaths.get(0);
		File file = new File(location);
		File[] files = file.listFiles();
		InputStream in = null;
		byte[] bt = null;
		
		int size = 0;
		for(File fileSon:files){
			/**
			 * 判断出为class文件时
			 */
			if(fileSon.isFile() && fileSon.getName().endsWith("EmployeeV1.class")){
				try {
					long length = fileSon.length();
					bt = new byte[(int) length];
					byte[] context = new byte[1024];
					in = new FileInputStream(fileSon);
					int tempbyte;
					while((tempbyte = in.read(context)) != -1){
						for(int i = 0;i < context.length;i++){
							System.arraycopy(context, 0, bt, size, tempbyte);
						}
						size = tempbyte;
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(in != null){
						try {
							in.close();
						} catch (IOException e) {
						}
					}
				}
				
			}
		}
		return bt;	
	}
	
	
	public void addClassPath(String path) {
		
		clzPaths.add(path);
		
	}
	
	
	
	public String getClassPath(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < clzPaths.size();i++){
			if(i == clzPaths.size() - 1){
				sb.append(clzPaths.get(i));
				break;
			}
			sb.append(clzPaths.get(i)).append(";");
			
		}
		return sb.toString();
	}

	

	

}
