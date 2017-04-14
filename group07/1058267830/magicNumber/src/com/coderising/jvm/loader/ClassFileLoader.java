package com.coderising.jvm.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClassFileLoader {
	
	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		String fileName = this.getClassPath() +"\\" + className.replace('.', '\\') + ".class";
		return loadClassFile(fileName);
	}
	
	private byte[] loadClassFile(String clzFileName) {
	
		File file = new File(clzFileName);
		InputStream in = null;
		List<Integer> list = new ArrayList<Integer>();
		byte[] result = null;
		try{
			
			in = new FileInputStream(file);
			int tempbyte;
			while((tempbyte = in.read()) != -1) {
				list.add(tempbyte);
			}
			result = new byte[list.size()];
			int i = 0;
			Iterator<Integer> iter = list.iterator();
			while(iter.hasNext()){
				result[i++] = iter.next().byteValue();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public void addClassPath(String path) {
		if(path != null){
			clzPaths.add(path);
		}
	}
	
	public String getClassPath_V1() {
		
		return null;
	}
	
	public String getClassPath() {
		
		return this.listToString();
	}
	
	private String listToString(){
		String result = "";
		if(clzPaths == null)
			return null;
		else{
			for(int i=0; i<clzPaths.size()-1; i++) {
				result = result + clzPaths.get(i) + ";";
			}
			result = result + clzPaths.get(clzPaths.size()-1);
		}
		return result;
	}
	
}
