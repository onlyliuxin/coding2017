package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		
		File classFile = getClassFileFromPath(className);	

		byte[] buffer = new byte[1024];
		try {
			FileInputStream fis = new FileInputStream(classFile);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			int readLen;
			while((readLen = fis.read(buffer))>-1){
				baos.write(buffer, 0, readLen);
			}
			
			return baos.toByteArray();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	public void addClassPath(String path) {
		File clzPath = new File(path);
		if(clzPath.exists() && clzPath.isDirectory()){
			this.clzPaths.add(path);
		}else{
			System.out.println("Invalid path:"+ path);
		}
	}
	
	
	
	public String getClassPath(){
		StringBuilder sb = new StringBuilder();
		Iterator it = this.clzPaths.iterator();
		while(it.hasNext()){
			if(sb.length()>0){
				sb.append(";");
			}
			sb.append(it.next());
		}
		return sb.toString();
	}

	public File getClassFileFromPath(String className) {
		Iterator it = this.clzPaths.iterator();

		//replace "." with "\\" in windows
		String fullclassPath = className.replaceAll("\\.", (File.separatorChar=='\\')?"\\\\":"/")+".class";
		
		while(it.hasNext()){
			File clzFile;
			String path = (String)it.next();
			if(path.endsWith(String.valueOf(File.separatorChar))){
				clzFile = new File(path+fullclassPath);
			}else{
				clzFile = new File(path+File.separatorChar+fullclassPath);
			}
			
			//Check file before further proceed
			if(clzFile.exists()&&clzFile.isFile()){
				return clzFile;
			}
		}
		
		throw new RuntimeException("Class not found:"+className);
	}

	

}
