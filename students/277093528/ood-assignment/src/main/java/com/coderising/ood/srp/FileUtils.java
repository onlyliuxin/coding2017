package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {
	/**
	 * 解析文件内容
	 * @return
	 * @throws IOException
	 */
	public static Product readFile() throws IOException {
		
		BufferedReader br = null;
		try {
			Product product = new Product();
			File file = new File( ConfigurationKeys.MESSAGE_FILE_PATH );
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			product.setProductID(data[0]); 
			product.setProductDesc(data[1]); 
			
			System.out.println("产品ID = " + data[0] + "\n");
			System.out.println("产品描述 = " + data[1] + "\n");
			return product;

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
		
	}
	
}
