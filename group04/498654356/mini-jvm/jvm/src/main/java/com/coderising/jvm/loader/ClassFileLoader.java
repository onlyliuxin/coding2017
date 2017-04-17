package com.coderising.jvm.loader;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;



public class ClassFileLoader {

	private static final String CLASS_SUFFIX = ".class";
	private static final byte[] EMPTY_BYTES = new byte[0];
	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		if(StringUtils.isEmpty(className)) {
			return EMPTY_BYTES;
		}
		String child = className.replaceAll("\\.", "\\\\") + CLASS_SUFFIX;
		for (String parent: clzPaths) {
			File file = new File(parent, child);
			if(file.exists()) {
				return doReadBinaryCode(file);
			}
		}
		return EMPTY_BYTES;	
	}
	
	
	private byte[] doReadBinaryCode(File file) {
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		try {
			fis = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int len = 0;
			while((len = fis.read(b)) > 0) {
				baos.write(b, 0, len);
			}
			return baos.toByteArray();
		} catch (Exception e) {
			new RuntimeException(e);
		} finally {
			close(baos);
			close(fis);
		}
		return EMPTY_BYTES;
	}


	private void close(Closeable stream) {
		if(stream != null ) {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	public String getClassPath(){
		StringBuilder builder = new StringBuilder();
		for (String path : clzPaths) {
			builder.append(path).append(";");
		}
		if(builder.length() > 0) {
			builder = builder.deleteCharAt(builder.length() - 1);
		}
		return builder.toString();
	}
	
}
