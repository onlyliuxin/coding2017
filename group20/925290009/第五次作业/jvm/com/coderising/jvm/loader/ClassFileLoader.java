package com.coderising.jvm.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.coderising.jvm.clasfile.ClassFile;

public class ClassFileLoader {

	private List<String> list = new ArrayList<String>();

	public ClassFileLoader() {

	}

	public void addClassPath(String path) {
		if (list.contains(path)) {
			return;
		}
		list.add(path);
	}

	public String getClassPath() {
		if (list.size() == 0 || list == null) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				stringBuilder.append(list.get(i));
			} else {
				stringBuilder.append(list.get(i)).append(";");
			}

		}
		return stringBuilder.toString();
	}

	public byte[] readBinaryCode(String className){

		String clzName = className.replace(".", File.separator) + ".class";;
		
		for(String path : list){
			String fileName = path + File.separator + clzName;
			byte[] codes = loadClassFile(fileName);
			if (codes != null) {
				return codes;
			}
		}
		return null;
	}

	private byte[] loadClassFile(String fileName){
		
		BufferedInputStream bis = null;
		File classFile = new File(fileName);
		try {
			bis = new BufferedInputStream(new FileInputStream(classFile));
			byte[] bytes_code = new byte[1024];
			int len = 0;
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			while((len = bis.read(bytes_code)) != -1){
				baos.write(bytes_code, 0, len);
			}
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		finally{
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public ClassFile loadClass(String className) {
		
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser clzPaser = new ClassFileParser();
		return clzPaser.parse(codes);
	}


}
