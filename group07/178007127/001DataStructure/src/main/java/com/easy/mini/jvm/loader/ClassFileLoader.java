package com.easy.mini.jvm.loader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.easy.core.AppUtils;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) throws Exception {
		String filePath = clzPaths.get(0)+AppUtils.packageName2Path(className)+".class";
		File file=new File(filePath);
		InputStream is=new FileInputStream(file);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		byte[] buff=new byte[1024];
		int len=0;
		while((len=is.read(buff, 0, buff.length))>0){
			baos.write(buff, 0, len);
		}
		return baos.toByteArray();	
	}
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	public String getClassPath(){
		StringBuilder sb=new StringBuilder();
		for (String path : clzPaths) {
			sb.append(path+";");
		}
		String sPath =sb.toString();
		return sPath.substring(0, sPath.length()-1);
	}

}
