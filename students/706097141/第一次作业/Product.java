package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 产品类
 * @author Administrator
 *
 */
public class Product {
	
	/**
	 * 产品ID
	 */
	private String productID;
	
	/**
	 * 产品描述
	 */
	private String productDesc;

	public String getProductID() {
		return productID;
	}

	public  void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	public static Product readFile(File file) throws IOException // @02C
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			Product product = new Product();
			product.setProductID(data[0]);
			product.setProductDesc(data[1]); 
			System.out.println("产品ID = " + product.getProductID() + "\n");
			System.out.println("产品描述 = " + product.getProductDesc() + "\n");
			return product;
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}
}
