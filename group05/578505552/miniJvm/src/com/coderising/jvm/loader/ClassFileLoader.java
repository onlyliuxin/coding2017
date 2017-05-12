package com.coderising.jvm.loader;

import com.coderising.jvm.clz.ClassFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();

	public byte[] readBinaryCode(String className) {

		className = className.replace('.', File.separatorChar) +".class";

		for(String path : clzPaths){
			String clzFileName = path + File.separatorChar + className;
			byte[] codes = loadClassFile(clzFileName);
			if(codes != null){
				return codes;
			}
		}

		return null;
	}

	public void addClassPath(String path) {
		if(clzPaths.contains(path)){
			return;
		}
		clzPaths.add(path);
	}

	public ClassFile loadClass(String className) {
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);
	}

	public String getClassPath(){

		StringBuffer buffer = new StringBuffer();

		for(int i = 0;i < clzPaths.size(); i++){
			buffer.append(clzPaths.get(i));
			if(i < clzPaths.size() - 1){
				buffer.append(";");
			}
		}

		return buffer.toString();
	}

	private byte[] loadClassFile(String clzFileName) {

		BufferedInputStream bis = null;
		try {
			File f = new File(clzFileName);
			bis = new BufferedInputStream(new FileInputStream(f));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			byte[] buffer = new byte[1024];
			int length = -1;

			while((length = bis.read(buffer)) != -1){
				bos.write(buffer, 0, length);
			}
			byte [] codes = bos.toByteArray();
			return codes;

		} catch(IOException e){
			e.printStackTrace();

		} finally{
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;

	}

}