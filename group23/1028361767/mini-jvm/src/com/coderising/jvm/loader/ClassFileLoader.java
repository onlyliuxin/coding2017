package com.coderising.jvm.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		className = castClassName(className);
		File file = null;
		FileInputStream fis = null;
		for(String clzPath : clzPaths){
			file = new File((clzPath + className));
			try {
				fis = new FileInputStream(file);
				byte[] bytes = new byte[2048];
				int i = 0;
				int sum = 0;
				while((i = fis.read(bytes)) != -1){
					sum += i;
				}
				fis.close();
				return Arrays.copyOf(bytes, sum);
			} catch (FileNotFoundException e) {
				continue;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new byte[0];
	}
	
	private String castClassName(String className) {
		String[] packs = className.split("\\.");
		StringBuilder sb = new StringBuilder("/");
		for(int i=0;i<packs.length;i++){
			sb.append(packs[i]);
			if(i == packs.length-1){
				sb.append(".class");
			}else{
				sb.append("/");
			}
		}
		return sb.toString();
	}

	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuilder sb = new StringBuilder();
		for(String clzPath : clzPaths){
			sb.append(clzPath).append(";");
		}
		return sb.length() == 0?"":sb.substring(0, sb.length()-1);
	}

	

	

}
