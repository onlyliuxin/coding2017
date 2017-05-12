package com.coderising.jvm.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.coderising.jvm.clz.ClassFile;


public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className)  {
		
		className = className.replace('.', File.separatorChar) + ".class";
		
		for (String path : this.clzPaths) {
			
			String clzFileName = path + File.separatorChar +className;
			byte[] codes = loadClassFile(clzFileName);
			if (codes != null) {
				return codes;
			}
		}
		return null;
		/*
		if (clzPaths.size() == 0) {
			throw new ClassNotFoundException(className);
		}
		String actualPath = getActualPath(className);
		
		File f = new File(actualPath);  
		
        if (!f.exists()) {  
            throw new ClassNotFoundException(actualPath);  
        }  
  
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());  
        BufferedInputStream is = null;  
        try {  
            is = new BufferedInputStream(new FileInputStream(f));  
            
            byte[] buffer = new byte[1024];  
            int len = 0;  
            
            while (-1 != (len = is.read(buffer))) {  
                bos.write(buffer, 0, len);  
            } 

            return bos.toByteArray();  
            
        } catch (IOException e) {  
            e.printStackTrace();  
            throw e;  
        } finally {  
            is.close();
            bos.close();  
        } */ 
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

	/*private String getActualPath(String className) {
		
		String fileName = className.substring(className.lastIndexOf(".") + 1) + ".class";
		String dirPath = className.substring(0, className.lastIndexOf(".")).replace(".", "\\");
		
		return clzPaths.get(clzPaths.size() - 1) + "\\" + dirPath + "\\" + fileName;   //classPath  取最近添加的一个
		
	}*/

	public void addClassPath(String path) {

		if (this.clzPaths.contains(path)) {
			return;
		}

		this.clzPaths.add(path);

	}

	public String getClassPath() {

		/*if (clzPaths.size() == 0) {
			return "";
		}

		StringBuffer buffer = new StringBuffer("");

		for (String str : clzPaths) {
			buffer.append(str);
			buffer.append(";");
		}

		return buffer.substring(0, buffer.length() - 1);*/// 去除最后一个分号
		return StringUtils.join(clzPaths, ";");

	}
	
	public ClassFile loadClass(String className) {
		
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);
		
	}
	

}
