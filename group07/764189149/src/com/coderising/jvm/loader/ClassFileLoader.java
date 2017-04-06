package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	private static final int MAX_BUFFER_SIZE = 1024;
	
	@SuppressWarnings("unused")
	public byte[] readBinaryCode(String className) throws IOException, ClassNotFoundException {
		String fileName = className.replace('.', '\\')+".java";
		String classPath = getClassPath();
		String[] array = classPath.split(";");
		String filePath = null;
		for (int i = 0; i < array.length; i++) {
			if(new File(array[i]+"\\"+fileName).exists()){
				filePath = array[i]+"\\"+fileName;
			}
		}

		if(null == fileName){
			throw new ClassNotFoundException(className);
		}
		FileInputStream is = new FileInputStream(filePath);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] bytes = new byte[MAX_BUFFER_SIZE];
		int length = 0;
		while( -1 != (length = is.read(bytes))){
			bos.write(bytes, 0, length);
		}
		is.close();
		bos.flush();
		return bos.toByteArray();	
	}
	
	
	public void addClassPath(String path) {
		if(!clzPaths.contains(path)){
			clzPaths.add(path);
		}
	}
	
	
	
	public String getClassPath(){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < clzPaths.size(); i++) {
			if(i == clzPaths.size() - 1){
				sb.append(clzPaths.get(i));
			}else{
				sb.append(clzPaths.get(i)).append(";");
			}
		}
		return sb.toString();
	}

	

	

}
