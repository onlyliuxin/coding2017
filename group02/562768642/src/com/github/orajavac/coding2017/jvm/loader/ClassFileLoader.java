package com.github.orajavac.coding2017.jvm.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {
	
	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		byte[] b = null;
		try{
			File clfile = new File(packageToPath(className));
			@SuppressWarnings("resource")
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(clfile));
			ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
			byte[] buffer = new byte[1024];
			int size = 0;         
	        while ((size = in.read(buffer)) != -1) {         
	            out.write(buffer, 0, size);         
	        }
	        b = out.toByteArray();
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;		
	}
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuffer buffer = new StringBuffer();
		for (int i=0;i<clzPaths.size();i++){
			buffer.append(clzPaths.get(i)).append(";");
		}
		return buffer.toString().substring(0,buffer.length()-1);
	}
	
	public String packageToPath(String className){
		return clzPaths.get(0)+className.replace(".", "/")+".class";
	}
}
