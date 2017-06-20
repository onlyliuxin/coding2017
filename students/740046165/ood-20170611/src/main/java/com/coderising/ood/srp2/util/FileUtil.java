package com.coderising.ood.srp2.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileUtil {
	
	private FileUtil() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 读取文件，形成流
	 * @param path
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static InputStream getClassLoaderFileInputStream(String path) throws FileNotFoundException {
		
		InputStream fis = FileUtil.class.getClassLoader().getResourceAsStream(path);
		if (null == fis) {
			throw new FileNotFoundException(path + "下文件不存在");
		}
		return fis;
	}
	
	
	
	/**
	 * 读取文件，形成流
	 * @param path
	 * @return
	 * @throws FileNotFoundException 
	 */
	public static InputStream getRelativePathFileInputStream(String path) throws FileNotFoundException {
		File f = new File(path);
		InputStream fis = new FileInputStream(f);
		
		return fis;
	}
	
	
	
	
	
	
}	
