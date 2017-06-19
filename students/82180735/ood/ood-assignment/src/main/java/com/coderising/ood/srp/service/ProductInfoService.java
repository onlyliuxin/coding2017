package com.coderising.ood.srp.service;

import com.coderising.ood.srp.domain.product.ProductInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * Created by justin on 17/6/19.
 */
public class ProductInfoService {

	public ProductInfo selectProduct() {
//		File file = new File("C:\\coderising\\workspace_ds\\ood-example\\src\\product_promotion.txt");
		URL base = Thread.currentThread().getContextClassLoader().getResource("");
		File file = new File(base.getFile(),"product_promotion.txt");
		BufferedReader br = null;
		ProductInfo productInfo = new ProductInfo();
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");

			productInfo.setProductID(data[0]);
			productInfo.setProductDesc(data[1]);

			System.out.println("产品ID = " + productInfo.getProductID() + "\n");
			System.out.println("产品描述 = " + productInfo.getProductDesc() + "\n");

		} catch (IOException e) {
//			throw new IOException(e.getMessage());
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return productInfo;
	}
}
