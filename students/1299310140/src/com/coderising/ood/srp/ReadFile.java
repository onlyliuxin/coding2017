package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	
	protected static Product readProductFile(String productFilePath) throws IOException
	{
		File file = new File(productFilePath);
		
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			Product product = new Product(data[0],data[1]);
			
			System.out.println("产品ID = " + product.getId() + "\n");
			System.out.println("产品描述 = " + product.getDesc() + "\n");
			
			return product;

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}
}
