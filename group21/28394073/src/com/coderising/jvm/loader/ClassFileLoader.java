package com.coderising.jvm.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clz.ClassFile;



public class ClassFileLoader {

	private static final PrintStream err = null;
	private static final int BUFFER_SIZE = 512;
	
	//这里为什么用数组保存路径？不用String?
	//(ArrayList底层采用Object类型的数组实现，当使用不带参数的构造方法生成ArrayList对象时，
	//实际上会在底层生成一个长度为10的Object类型数组。)
	
	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		String classPath = className.replace(".", "\\");
		String path1=this.getClassPath() + "\\" +classPath+".class";
		System.out.print(path1);
		FileInputStream is = new FileInputStream(path1);
		BufferedInputStream bs = new BufferedInputStream(is);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int length=0;
		while(true){
			int mul = 0;
			length = bs.read(buffer);
			if(length == BUFFER_SIZE){
				baos.write(buffer);
				mul++;
				}
			if(length>0 && length<BUFFER_SIZE){
				baos.write(buffer, mul*BUFFER_SIZE, length);
			}
			if(length<0){
				break;
			}
		}
		
		return baos.toByteArray();
	}
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		String finalPath = "";
		for(int i=0;i<clzPaths.size();i++){
			finalPath = finalPath + clzPaths.get(i);
			if(i!=clzPaths.size()-1){
				finalPath = finalPath + ";";
			}
		}
		return finalPath;
	}


	public ClassFile loadClass(String className) {
		// TODO Auto-generated method stub
		ClassFileParser parser = new ClassFileParser();
		ClassFile clzFile = null;
		try {
			clzFile = parser.parse(this.readBinaryCode(className));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clzFile;
	}

	

	

}
