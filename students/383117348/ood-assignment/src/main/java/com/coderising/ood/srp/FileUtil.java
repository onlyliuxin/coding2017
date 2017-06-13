package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	
	/**
	 * 根据文件路径获取文件,如果文件不存在,抛出异常
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File readFile(String path) throws FileNotFoundException {
		File file = new File(path);
		if (!file.exists()) {
			throw new FileNotFoundException("文件不存在");
		}
		return file;
	}

	/**
	 * 根据正则,将文件中的数据解析成字符串数组形式返回
	 * 
	 * @param file
	 * @param regex
	 * @return
	 */
	public static List<String[]> parseToString(File file, String regex) {
		List<String[]> list = new ArrayList<String[]>();
		BufferedReader br = null;
		try {
			if (file != null && file.exists()) {

				br = new BufferedReader(new FileReader(file));
				String temp = null;
				while ((temp = br.readLine()) != null) {
					String[] strs = temp.split(regex);
					list.add(strs);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}
}
