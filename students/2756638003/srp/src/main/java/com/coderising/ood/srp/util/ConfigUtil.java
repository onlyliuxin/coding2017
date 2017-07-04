package com.coderising.ood.srp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置文件读写工具类
 * 
 */
public class ConfigUtil {

	public static Map<String, String> readTextFile(File file)
			throws IOException {
		Map<String, String> conf = new HashMap<String, String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file));) {
			String temp = null;
			while ((temp = br.readLine()) != null) {
				int indexOf = temp.indexOf(' ');
				if (indexOf > -1) {
					conf.put(temp.substring(0, indexOf),
							temp.substring(indexOf));
				}
			}
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		return conf;
	}
}
