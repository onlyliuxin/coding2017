package com.coderising.jvm.loader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

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
	 public byte[] readBinaryCodeLong(String className){
	     className = className.replace('.', File.separatorChar) + ".class";
	     String path = findCorrectClassPath(className);
	     if(path == null){
	         System.out.println("can not find class file in class paths");
	         return null;
	     }
	     
	     String classPathComplete = path + className;
	     loadClassFileLong(classPathComplete);
	     
	 }
	
	private byte[] loadClassFileLong(String path) {
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        try {
            byte[] buffer = new byte[1024];
            
            int length;
            bis = new BufferedInputStream(new FileInputStream(path));
            while((length = bis.read(buffer)) != -1){
                baos.write(buffer, 0, length);
            }
            
            baos.toByteArray(); 
        } catch (IOException e) {
            // TODO: handle exception
        } finally

    }
	
    private String findCorrectClassPath(String className) {
        for (int i = 0; i < clzPaths.size(); i++) {
            String filePath = clzPaths.get(i) + File.separatorChar + className;
            File classFile =  new File(filePath);
            if(classFile.exists()){
                return clzPaths.get(i);
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
	
	
	
	public String getClassPath(){
		return StringUtils.join(this.clzPaths,";");
	}

	public ClassFile loadClass(String className) {
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);
		
	}
	
	
	
	// ------------------------------backup------------------------
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
	
	private byte[] loadClassFile_V1(String clzFileName) {
		
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