package com.bruce.homework0402.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClassFileLoader {

	private List<String> clzPaths = new ArrayList<>();

	//TODO 方法需要调试
	public byte[] readBinaryCode(String className) {
		InputStream is = null;
		try {
			className = className.replace(".", "/");
			String path = getClassPath() + "/" + className +".class";
			URL url = new URL(path);
			byte[] buff = new byte[1024*2];
			int len = -1;
			is = url.openStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			while((len = is.read(buff)) != -1) {
				outputStream.write(buff, 0, len);
			}
			return outputStream.toByteArray();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;	
	}
	
	
	public void addClassPath(String path) {
		clzPaths.add(path);
	}

	public String getClassPath() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < clzPaths.size(); i++) {
			sb.append(clzPaths.get(i));
			if(i != (clzPaths.size()-1)) {
				sb.append(";");
			}
		}
		return sb.toString();
	}
}
