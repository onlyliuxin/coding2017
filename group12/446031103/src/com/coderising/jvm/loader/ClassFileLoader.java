package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;



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
	
	private byte[] loadClassFile(String clzFileName) {
		
		File f = new File(clzFileName);
		
		try {
		
			return IOUtils.toByteArray(new FileInputStream(f));
			
		} catch (IOException e) {			
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public void addClassPath(String path) {
		if(this.clzPaths.contains(path)){
			return;
		}
		
		this.clzPaths.add(path);
		
	}
	
	 public String getClassPath_V1(){
			
			StringBuffer buffer = new StringBuffer();
			for(int i=0;i<this.clzPaths.size();i++){
				buffer.append(this.clzPaths.get(i));
				if(i<this.clzPaths.size()-1){
					buffer.append(";");
				}
			}
			return buffer.toString();
	}
	
	 public String getClassPath(){
			return StringUtils.join(this.clzPaths,";");
	}

	

	

}
