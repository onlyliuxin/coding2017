package com.coderising.ood.answer.utils;

import java.io.BufferedReader;
import java.io.File;
import java.net.URI;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 文件读取工具
 * @author readke
 *
 */
public class FileUtils {
	private static final Logger log = LogManager.getLogger(FileUtils.class);
	public static File readFile(){
		
		File file = null;
		BufferedReader br = null;
		
		try {
			URL url = FileUtils.class.getClassLoader().getResource(ConfigUtils.getProperty("product.txt"));
			log.info(url.getPath());
			URI uri = url.toURI();
			file = new File(uri);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return file;
	}
	
	public static void main(String[] args) {
		readFile();
	}
}
