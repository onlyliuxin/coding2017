package com.vvv.jvm.loader;

import java.io.BufferedInputStream;
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
		for(int i=0;i<clzPaths.size();i++){
			String path = clzPaths.get(i)+ File.separatorChar+className.replace('.',File.separatorChar)+".class";
			System.out.println(path);
			byte[] fileByte = loadFile(path);	
			if(fileByte!=null){
				return fileByte;
			}
		}
		
		return null;	
	}
	
	private byte[] loadFile(String className){
		BufferedInputStream is = null; 
		try {
			File file = new File(className);
			is = new BufferedInputStream(new FileInputStream(file));
		    ByteArrayOutputStream os = new ByteArrayOutputStream();
		    byte[] buffer = new byte[1024];
			int length = -1;			
			while((length = is.read(buffer)) != -1){
				os.write(buffer, 0, length);				
			}
			return os.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public void addClassPath(String path) {
		if(clzPaths.contains(path))
			return;
		clzPaths.add(path);
	}
	
	public String getClassPath(){
		if(clzPaths.size()<=0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<clzPaths.size(); i++){
			sb.append(clzPaths.get(i));
			sb.append(";");
		}
		return sb.substring(0, sb.lastIndexOf(";"));
	}
	
}
