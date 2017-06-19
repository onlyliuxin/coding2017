package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProductDao {

	public Product getProduct(String productFileName) throws IOException {
		BufferedReader br = null;
		Product product = null;
		
		try {
			File file = new File(productFileName);
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			String productID = data[0];
			String productDesc = data[1];
			
			System.out.println("产品ID = " + productID + "\n");
			System.out.println("产品描述 = " + productDesc + "\n");
			product = new Product();
			product.setProductID(productID);
			product.setProductDesc(productDesc);
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
		
		return product;
	}
}
