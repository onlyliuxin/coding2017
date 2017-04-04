package com.github.ipk2015.coding2017.minijvm.loader;



import java.io.BufferedInputStream;
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
		className=getCompleteClassName(className);
		File file=null;
		for(String path:clzPaths){
			file=new File(path+"\\"+className);
			if(file.exists()){
				break;
			}
		}
		if(null==file){
			throw new FileNotFoundException(className);
		}
		ByteArrayOutputStream bos=new ByteArrayOutputStream((int)file.length());
		BufferedInputStream in=new BufferedInputStream(new FileInputStream(file));
		int size=1024;
		byte[] buffer=new byte[size];
		int length=0;
		while((length=in.read(buffer, 0, size))!=-1){
			bos.write(buffer,0,length);
		}
		return bos.toByteArray();	
	}
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuffer buffer=new StringBuffer();
		for(String path:clzPaths){
			buffer.append(path+";");
		}
		buffer.deleteCharAt(buffer.length()-1);
		return buffer.toString();
	}

	private String getCompleteClassName(String name){
		if(!name.endsWith(".class")){
			name=name+".class";
		}
		int pointPos=name.lastIndexOf(".", name.length()-7);
		if(pointPos>-1){
			name=name.substring(pointPos+1);
		}
		return name;
	}

	

}
