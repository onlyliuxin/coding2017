package com.coderising.ood.srp.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.srp.entity.Product;

public class ProductService {
	
	/**
	 * 从给定的路径文件获取打折商品信息
	 * @param path
	 * @return
	 */
	public List<Product> getPromotionProducts(String path){
		List<Product> products = new ArrayList<Product>();
		if(path != null && !"".equals(path)){
			File file = new File(path);
			try {
				products = readFile(file);
			} catch (IOException e) {
				System.out.println("获取打折商品信息出错："+ e.getMessage());
			}
		}
		return products;
	}
	
	private List<Product> readFile(File file) throws IOException // @02C
	{
		List<Product> products = new ArrayList<Product>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = null;
			while((temp = br.readLine()) !=  null){
				
				String[] data = temp.split(" ");
				
				Product product = new Product();
				product.setProductId(data[0]);
				product.setProductDesc(data[1]);
				System.out.println(product);
				
				products.add(product);
			}
			return products;
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			if(br != null){
				br.close();
			}
		}
	}
}
