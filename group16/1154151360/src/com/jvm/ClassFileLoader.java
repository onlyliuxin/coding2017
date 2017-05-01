package com.jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) throws IOException {
		className = className.replace('.', '\\').replace("\\java", ".class");
		String classNamePath = clzPaths.get(0)+className;
		 FileInputStream in = new FileInputStream(new File(classNamePath));
		 byte [] cach = new byte[1024];
		 int i = 0;
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 while ((i = in.read(cach)) != -1){
			 out.write(cach, 0, i);
		 }
		return out.toByteArray();	
		
		
	}
	
	
	public void addClassPath(String path) {
		if (path != null)
			clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		return null;
	}
}
