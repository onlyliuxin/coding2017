package com.github.orajavac.coding2017.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	public static Product readFile(File file) throws IOException // @02C
	{
		BufferedReader br = null;
		Product p = new Product();
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			p.setProductID(data[0]); 
			p.setProductDesc(data[1]); 
			
			System.out.println("产品ID = " + p.getProductID() + "\n");
			System.out.println("产品描述 = " + p.getProductDesc() + "\n");

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
		return p;
	}
}
