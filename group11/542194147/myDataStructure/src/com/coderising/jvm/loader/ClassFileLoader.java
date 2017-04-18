package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	private static final int BUFFER_SIZE=1024;
	
	public byte[] readBinaryCode(String className) {
		className=className.replace(".","\\")+".class";
		String absolutePath=null;
		for(int i=0;i<clzPaths.size();i++){
			absolutePath=clzPaths.get(i)+"\\"+className;
			byte[]codes=loadClassFile(absolutePath);
			if(codes!=null){
				return codes;
			}
		}
		return null;
	}


	private byte[] loadClassFile(String absolutePath) {
		File file=new File(absolutePath);
		FileInputStream fis = null;
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		byte[]buffer=new byte[BUFFER_SIZE];
		try {
			fis=new FileInputStream(file);
			while(baos.size()<file.length()){
				int len=fis.read(buffer);
				if(len<0){
					break;
				}
				baos.write(buffer, 0, len);
			}
			if(baos.size()>file.length()){
				return Arrays.copyOf(baos.toByteArray(), (int) file.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fis.close();
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return baos.toByteArray();
	}
	
	
	public void addClassPath(String path) {
		if(this.clzPaths.contains(path)){
			return;
		}
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<clzPaths.size();i++){
			if(i==0){
				sb.append(clzPaths.get(i));
			}else{
				sb.append(";").append(clzPaths.get(i));
			}
			
		}
		return sb.toString();
	}

	

	

}
