package com.coderising.ood.srp.chasing.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.coderising.ood.srp.chasing.model.Product;

public class ProductService {
	File f;
	public ProductService(File f){
		this.f = f;
	}
	/** 获取促销商品 */
	public Product loadProduct() throws IOException // @02C
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			System.out.println("产品ID = " + data[0] + "\n");
			System.out.println("产品描述 = " + data[1] + "\n");
			return new Product(data[0], data[1]);
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}
}
