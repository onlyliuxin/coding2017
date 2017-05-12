package com.coderising.jvm.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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

	private byte[] loadClassFile(String fileName) {
		ArrayList<Byte> list=new ArrayList<Byte>();
		try {
			InputStream in=new FileInputStream(fileName.toString());
			int length=-1;
			byte[] buffer=new byte[1024];
			while ((length=in.read(buffer))!=-1) {
				int size=list.size();
				for (int i = size; i < size+length; i++) {
					list.add(buffer[i-size]);
				}	
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] byteCodes=new byte[list.size()];
		for (int i = 0; i < byteCodes.length; i++) {
			byteCodes[i]=list.get(i);
		}
		
		return byteCodes;
	}
	
	public ClassFile loadClass(String className) {
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);
		
	}
	public void addClassPath(String path) {
		clzPaths.add(path);
		
	}
	
	
	
	public String getClassPath(){
		String string="";
		for (int i = 0; i < clzPaths.size(); i++) {
			string=i==0?string+clzPaths.get(i):string+";"+clzPaths.get(i);
		}
		return string;
	}

	

	

}
