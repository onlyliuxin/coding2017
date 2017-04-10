package com.coderising.jvm.loader;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		StringBuffer fileName=new StringBuffer(clzPaths.get(0));
		String nameString[]=className.split("\\.");
		fileName.append("\\");
		fileName.append(nameString[nameString.length-1]);
		fileName.append(".class");
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
