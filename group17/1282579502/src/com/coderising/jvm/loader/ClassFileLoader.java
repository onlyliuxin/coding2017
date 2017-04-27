package com.coderising.jvm.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		String classPath = convertToFilePath(className);
		File targetFile = null;
		for(int i = 0; i< clzPaths.size(); i++){
			String fullPath = clzPaths.get(i)+File.separator+classPath;
			System.out.println("path: " + fullPath); 
			File temp = new File(fullPath);
			if(temp.exists()) {
				targetFile = temp;
				break;
			}
		}
		
		if(targetFile != null){
			System.out.println("targetFile: " + targetFile.getAbsolutePath());
		}
		long fileLength = targetFile.length();
		System.out.println("File length: " + fileLength); 
		byte[] byteArray = new byte[(int)fileLength];
		FileInputStream is = null;
		try {
			is = new FileInputStream(targetFile);
			is.read(byteArray);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		return byteArray;	
		
		
	}
	
	private String convertToFilePath(String className){
		return className.replaceAll("\\.", File.separator) + ".class";
	}
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuilder sb = new StringBuilder();
		for(String item : clzPaths){
			sb.append(item + ";");
		}
		return sb.substring(0, sb.length()-1);
	}

	

	

}