package com.github.HarryHook.coding2017.jvm.loader;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className)	{
	
	  
	    String fileName = clzPaths.get(0) + File.separatorChar + 
		    className.replace('.', File.separatorChar) + ".class";
	    
	    InputStream in = null;
	    try {
		in = new FileInputStream(fileName);   
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
		    
		byte[] buffer = new byte[1024];
		int length = 0;
		while((length = in.read(buffer)) != -1) {
		    out.write(buffer, 0, length);
		}
		return out.toByteArray();
	    } catch(IOException e) {
		e.printStackTrace();
	    } finally {
		if(in != null) {
		    try{
			in.close();
		    }catch(IOException e) {
			e.printStackTrace();
		    }
		}
	    }
	    return null;
	    
	}
	
	
	public void addClassPath(String path) {
		
	    this.clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
	    
	    StringBuilder buffer =  new StringBuilder();
	    
	    for(int i=0; i<clzPaths.size(); i++) {
		
		buffer.append(clzPaths.get(i));
		if(i < clzPaths.size() - 1) {
		    buffer.append(";");
		}
	    }
	    return buffer.toString();
	}

}
