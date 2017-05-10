package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		String name = "";
		for (int i = 0; i < className.length(); i++) {
			if(className.charAt(i)=='.'){
				name +=  File.separatorChar;
			}else{
				name += className.charAt(i);
			}
		}
		File file = new File(getClassPath()+ File.separatorChar  +name+".class");
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			in = new FileInputStream(file);
			out = new ByteArrayOutputStream();
			byte[] buff = new byte[1024*2];
			int len = 0;
			while((len=in.read(buff))!=-1){
				out.write(buff, 0, len);
			}
			return out.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;	
		
		
	}
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuilder sb = new StringBuilder();
		for (String string : clzPaths) {
			sb.append(string).append(";");
		}
		sb = sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}