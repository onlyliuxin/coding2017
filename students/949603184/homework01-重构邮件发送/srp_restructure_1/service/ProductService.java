package com.coderising.ood.srp_restructure_1.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.srp_restructure_1.pojo.Product;

public class ProductService {

	public List<Product> getProductDescList(File file) throws IOException {
		List<Product> plist = new ArrayList<Product>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");

			Product p = new Product();
			p.setProductID(data[0]);
			p.setProductDesc(data[1]);

			System.out.println("产品ID = " + p.getProductID() + "\n");
			System.out.println("产品描述 = " + p.getProductDesc() + "\n");

			plist.add(p);
			return plist;
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}

	}

}
