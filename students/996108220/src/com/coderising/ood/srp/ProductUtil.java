package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProductUtil {
	private String productConfigPath="D:\\JavaCoding\\students\\996108220\\src"
			+ "\\com\\coderising\\ood\\srp\\product_promotion.txt";

	private String[] readFile() throws IOException // @02C
	{
		File file=new File(productConfigPath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");;
			return data;

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}

	public String getProductID() throws IOException {
		String[] data= readFile();
		return data[0];
	}

	public String getProductDesc() throws IOException {
		String[] data= readFile();
		return data[1];
	}
	
}
