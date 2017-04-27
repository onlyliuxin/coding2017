package com.coderising.jvm.loader;

import com.coderising.jvm.clz.ClassFile;
import org.junit.Assert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<>();
	
	public byte[] readBinaryCode(String className) {

		String clzFileName = className.replaceAll("\\.", "/") + ".class";
		// 查找文件
		boolean isFind = false;
		File clzFile = null;
		for (String path : clzPaths) {
			File file = new File(path + clzFileName);
			if (file.exists()) {
				isFind = true;
				clzFile = file;
				break;
			}
		}
		if (!isFind) {
			throw new RuntimeException("找不到类: " + className);
		}
		ByteArrayOutputStream bao = new ByteArrayOutputStream();;
		try (
			FileInputStream fis = new FileInputStream(clzFile);
		) {
			byte[] buffer = new byte[4096];
			int length = 0;
			while ((length = fis.read(buffer)) != -1) {
				bao.write(buffer, 0, length);
			}
			bao.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bao.toByteArray();
	}

	public ClassFile loadClass(String className) {

		byte[] byteCode = readBinaryCode(className);
		// 此处应有字节码处理
		ClassFileParser classFileParse = new ClassFileParser();
		return classFileParse.parse(byteCode);
	}
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){

		StringBuilder classPaths = new StringBuilder();
		for (String clsPath : clzPaths) {
			classPaths.append(clsPath).append(";");
		}
		classPaths.deleteCharAt(classPaths.length() - 1);
		return classPaths.toString();
	}

	

	

}
