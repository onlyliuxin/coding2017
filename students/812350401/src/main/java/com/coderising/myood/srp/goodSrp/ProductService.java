package com.coderising.myood.srp.goodSrp;


import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class ProductService {
	private static File f = new File("/Users/thomas_young/Documents/code/liuxintraining/coding2017/students/812350401/src/main/java/com/coderising/myood/srp/product_promotion.txt");
	public List<Product> getPromotionProducts() {
		//从文本文件中读取文件列表
		String line;
		List<Product> products = new LinkedList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(f))) {
			while ((line = br.readLine()) != null) {
				Product p =parseGenProduct(line);
				products.add(p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return products;
	}

	private Product parseGenProduct(String line) {
		String[] data = line.split(" ");
		String productID = data[0];
		String productDesc = data[1];
		System.out.println("产品ID = " + productID);
		System.out.println("产品描述 = " + productDesc + "\n");
		Product p = new Product();
		p.setDesc(productDesc);
		p.setId(productID);
		return p;
	}


}
