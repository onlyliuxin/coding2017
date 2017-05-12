package com.coding.basic.homework_04.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	private static final int BUFFER_SIZE = 1024;
	
	public byte[] readBinaryCode(String className) {
		byte[] result = null;
		for(String path : clzPaths){
			File file = new File(getPath(path, className));
			if(!file.exists()){
				continue;
			}
			result = readFile(file);	
		}
		return result;
	}
	
	/**
	 * 文件数据存放在字节数组中返回
	 * @param file
	 * @return
	 */
	private byte[] readFile(File file){
		FileInputStream fileInputStream;
		byte[] buffer = new byte[BUFFER_SIZE];
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		try {
			fileInputStream = new FileInputStream(file);
			while(byteArrayOutputStream.size() < file.length()){
				int len = fileInputStream.read(buffer);
				if(len < 0){
					break;
				}
				byteArrayOutputStream.write(buffer, 0, len);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(byteArrayOutputStream.size() > file.length()){
			byte[] result = byteArrayOutputStream.toByteArray();
			return Arrays.copyOf(result, (int)file.length());
		}
		return byteArrayOutputStream.toByteArray();
	}
	
	/**
	 * 获取真实路径路径
	 * @param className
	 * @return
	 */
	private String getPath(String path ,String className){
		System.out.println(className);
		String [] ways = className.split("\\.");
		for (String string : ways) {
			System.out.println(string);
		}
		StringBuilder builder = new StringBuilder();
		builder.append(path);
		for (String string : ways) {
			
			builder.append("\\");
			builder.append(string);
		}
		builder.append(".class");
		System.out.println(builder.toString());
		return builder.toString();
	}
	
	private byte[] loadClassFile(String clzFileName) {
		
		return null;
	}
	
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	public String getClassPath_V1(){
		
		return  null;
	}

	
	public String getClassPath(){
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < clzPaths.size(); i++){
			builder.append(clzPaths.get(i));
			if(i < clzPaths.size() - 1){
				builder.append(";");
			}
		}
		return builder.toString();
	}

	

	

}