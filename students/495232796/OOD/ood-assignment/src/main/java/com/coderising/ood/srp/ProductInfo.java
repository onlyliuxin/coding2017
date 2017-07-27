package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProductInfo {
	protected String productID = null;
	protected String productDesc = null;
	
	public ProductInfo(String path) {
		try {
			readFile(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void readFile(String path) throws IOException
	{
		File f = new File(path);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			this.productID = data[0];
			this.productDesc = data[1];
			
			System.out.println("产品ID = " + productID + "\n");
			System.out.println("产品描述 = " + productDesc + "\n");

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}
	
	public String getProductID() {
		return productID;
	}
	
	public String getProductDesc() {
		return productDesc;
	}
}
