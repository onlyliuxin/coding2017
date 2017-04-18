package com.github.wdn.coding2017.jvm.loader;

import com.github.wdn.coding2017.jvm.clz.ClassFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		File file = null;
		for (int i = 0; i < clzPaths.size(); i++) {
			String path = clzPaths.get(i);
			String fullPath = path +File.separator+ className.replace(".", File.separator)+".class";
			file = new File(fullPath);
		}

		try {
			if(file.exists()){
				// 使用FileUtils最简单
				// return FileUtils.readFileToByteArray(file);
				FileInputStream inputStream = new FileInputStream(file);
				long fileLength = file.length();
				if (fileLength>Integer.MAX_VALUE) {
					throw new IllegalArgumentException("Size cannot be greater than Integer max value: " + fileLength);
				}
				byte[] fileBytes = new byte[(int)fileLength];
				byte[] bytes = new byte[1024];
				int len;
				int offset=0;
				// for循环使用inputStream api读取 一次读完。。
				for(offset = 0; offset < fileLength && (len = inputStream.read(fileBytes, offset, (int)fileLength - offset)) != -1; offset += len) {
					;
				}
				// while循环使用System.arraycopy读取
				/*while ((len = inputStream.read(bytes))>-1){
					System.arraycopy(bytes, 0, fileBytes, offset, len);
					offset += len;
				}*/
				return fileBytes;
			}
			throw new FileNotFoundException();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	
	
	public String getClassPath(){
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < clzPaths.size(); i++) {
			if (i==clzPaths.size()-1) {
				stringBuffer.append(clzPaths.get(i));
			}else{
				stringBuffer.append(clzPaths.get(i)).append(";");
			}
		}
		return stringBuffer.toString();
	}


	public ClassFile loadClass(String className) {
		ClassFileParser classFileParser = new ClassFileParser();
		ClassFile classFile = classFileParser.parse(readBinaryCode(className));
		return classFile;
	}
}
