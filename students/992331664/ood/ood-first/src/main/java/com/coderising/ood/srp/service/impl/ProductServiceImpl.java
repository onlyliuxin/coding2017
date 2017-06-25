package com.coderising.ood.srp.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.srp.model.Product;
import com.coderising.ood.srp.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> doFindPromotionalProducts(File file) throws IOException {

		List<Product> products = new ArrayList<Product>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			while (br.read() != -1) {
				Product product = new Product();
				String temp = br.readLine();
				String[] data = temp.split(" ");
				product.setProductID(data[0]);
				product.setProductDesc(data[1]);
				products.add(product);
			}
			
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
		return products;
	}

}
