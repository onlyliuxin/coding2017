package com.coderising.jvm.loader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> list = new ArrayList<String>();

	public ClassFileLoader() {

	}

	public void addClassPath(String path) {
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

	public byte[] readBinaryCode(String className) throws ClassFileLoaderException {

		String fileName = getFileName(className);
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(fileName));
			byte[] bytes_code = new byte[1024];
			int len = 0;
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			while((len = bis.read(bytes_code)) != -1){
				baos.write(bytes_code, 0, len);
			}
			return baos.toByteArray();
		} catch (FileNotFoundException e) {
			throw new ClassFileLoaderException(e);
		} catch (IOException e) {
			throw new ClassFileLoaderException(e);
		}
		finally{
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					throw new ClassFileLoaderException(e);
				}
			}
		}
	}

	private String getFileName(String className) {
		StringBuilder stringBuilder = new StringBuilder();
		String folder = getClassPath();
		String packgeName = className.replace(".", "\\");

		stringBuilder.append(folder).append('\\').append(packgeName).append(".class");
		return stringBuilder.toString();
	}

}
