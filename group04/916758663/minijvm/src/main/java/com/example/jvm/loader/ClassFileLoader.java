package com.example.jvm.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		String filePartPath = className.replace(".", "/") + ".class";
		for (String clzPath : clzPaths) {
			String filePath = clzPath + "/" + filePartPath;
			File file = new File(filePath);
			if (file.exists()) {
				try {
					FileInputStream inputStream = new FileInputStream(file);
					int bytesRead = 0;
					int len = inputStream.available();
					byte[] buffer = new byte[len];
					while (bytesRead < len) {
						int result = inputStream.read(buffer, bytesRead, len - bytesRead);
						if (result == -1){
							break;
						}
						bytesRead += result;
					}
					inputStream.close();
					return buffer;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		throw new RuntimeException("未找到类");
	}
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}

	public String getClassPath(){
		String result = "";
		StringBuilder sb = new StringBuilder();
		for(String path : clzPaths){
			sb.append(path + ";");
		}
		result = sb.toString();
		if (result != "") {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	

	

}
