package com.coderising.jvm.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		className = className.replace('.', File.separatorChar) +".class";
		
		for(String path : this.clzPaths){
			
			String clzFileName = path + File.separatorChar + className;
			byte[] codes = loadClassFile(clzFileName);
			if(codes != null){
				return codes;
			}			
		}
		
		return null;
	}
	
	public ClassFile loadClass(String className) {
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);
		
	}

	public void addClassPath(String path) {
		if(clzPaths.contains(path))
			return ;
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuilder sb = new StringBuilder();
		for(String clzPath : clzPaths){
			sb.append(clzPath).append(";");
		}
		return sb.length() == 0?"":sb.substring(0, sb.length()-1);
	}

	private byte[] loadClassFile(String clzFileName) {
		
		BufferedInputStream bis = null;
		
		try {
			
			File f = new File(clzFileName);
			
						
			bis = new BufferedInputStream(new FileInputStream(f));
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			
			
			byte[] buffer = new byte[1024];
			int length = -1;
			
			while((length = bis.read(buffer)) != -1){
				bos.write(buffer, 0, length);				
			}
			
			byte [] codes = bos.toByteArray();
			
			return codes;
			
		} catch(IOException e){
			e.printStackTrace();
			
		} finally{
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
		return null;
	}	

	

}
