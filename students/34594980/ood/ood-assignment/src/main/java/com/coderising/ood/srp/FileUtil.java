package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

	private static final String FILENAME="product_promotion.txt";
	
	//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
	public static Product readFile()// @02C
	{
		Product product = null;
		BufferedReader br = null;
		try {
			String filePath = FileUtil.class.getResource(FILENAME).getPath();
			File file = new File(filePath);
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			product = new Product(data[0], data[1]);

		} catch (IOException e) {
				e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return product;
	}
}
