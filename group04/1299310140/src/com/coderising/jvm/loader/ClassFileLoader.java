package com.coderising.jvm.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) throws Exception{
		//com.coderising.jvm.test.EmployeeV1
		//"com\\coderising\\jvm\\test\\EmployeeV1"
		String clzFileName = this.getClassPath() + "\\" + className.replace(".", "\\") + ".class";
		//FileInputStream   BufferedInputStream   ByteArrayOutputStream
		File file = new File(clzFileName);
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		
		byte[] buffer = new byte[1024];
		
		int length = -1;
		
		while((length = bis.read(buffer)) != -1){
			bos.write(buffer,0,length);
		}
		
		byte[] result = bos.toByteArray();
		bis.close();
		bos.close();
		
		return result;	
	}
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	public String getClassPath(){
		String result = "";
		for(int i = 0;i < clzPaths.size();i++){
			result = result + clzPaths.get(i);
			if(i == clzPaths.size() - 1){
				break;
			}
			result = result + ";";
		}
		return result;
	}

}
