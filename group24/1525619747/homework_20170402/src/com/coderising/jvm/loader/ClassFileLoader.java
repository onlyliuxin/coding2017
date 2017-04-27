package com.coderising.jvm.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) throws IOException {
		String clzFileName = "";
		byte[] byteCodes = null;
		boolean classFound = false;
		
		for (String path : clzPaths) {
			clzFileName = path + File.separatorChar + className.replace('.', File.separatorChar) + ".class";

			if ((byteCodes = loadClassFile(clzFileName)) != null){
				classFound = true;
				return byteCodes;
			}			   
		}
		
		if (classFound == false) {
			throw new FileNotFoundException(clzFileName);
		}
		
		return null;		
	}
	
	private byte[] loadClassFile(String clzFileName) throws IOException {
		
		File file = new File(clzFileName);  
        if(!file.exists()){  
//            throw new FileNotFoundException(clzFileName);
        	return null;
        }
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int)file.length());
        BufferedInputStream in = null;
        
        try {
        	in = new BufferedInputStream(new FileInputStream(file));
        	
        	int buf_size = 1024;
        	byte[] buffer = new byte[buf_size];
        	int len = 0;
        	
        	while ((len = in.read(buffer, 0, buf_size)) != -1) {
        		bos.write(buffer, 0, len);
        	}
        	
        	return bos.toByteArray();
        	
        } catch (IOException e) {
        	e.printStackTrace();
        	throw e;
        } finally {
        	try {
        		in.close();
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        	bos.close();
        }
	}
	
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	public String getClassPath_V1(){
		
		return  null;
	}
	
	public String getClassPath(){
		String classPath = "";
		for (int i = 0; i < clzPaths.size(); i++) {
			classPath += clzPaths.get(i);
			if (i != clzPaths.size() - 1) {
				classPath += ";";
			}
		}
		return classPath;
	}

	

}
