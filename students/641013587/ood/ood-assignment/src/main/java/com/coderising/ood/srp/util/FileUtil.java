package com.coderising.ood.srp.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.coderising.ood.srp.entity.Product;

public class FileUtil {
	
	public static final String FILE_URL="C:\\Users\\Administrator\\git\\coding2017\\students\\641013587\\ood\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt";
	
	public static Product readRecommendProduct() throws IOException{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(FILE_URL));
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
