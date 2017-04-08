package com.coderising.jvm.loader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.coderising.jvm.clz.ClassFile;



public class ClassFileLoader {

	private static final String CLASS_SUFFIX = ".class";
	private static final byte[] EMPTY_BYTES = new byte[0];
	private List<String> clzPaths = new ArrayList<String>();
	
	public byte[] readBinaryCode(String className) {
		if(StringUtils.isEmpty(className)) {
			return EMPTY_BYTES;
		}
		String child = className.replace(".", File.separator) + CLASS_SUFFIX;
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
		try {
			fis = new FileInputStream(file);
			return IOUtils.toByteArray(fis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(fis);
		}
		return EMPTY_BYTES;
	}

	public void addClassPath(String path) {
		clzPaths.add(path);
	}
	
	public String getClassPath(){
		return StringUtils.join(clzPaths, ";");
	}


	public ClassFile loadClass(String className) {
		byte[] codes = this.readBinaryCode(className);
		ClassFileParser parser = new ClassFileParser();
		return parser.parse(codes);
	}
	
}
