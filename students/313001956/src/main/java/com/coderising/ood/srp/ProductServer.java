package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductServer {
	private static final String FILE_NAME = "D:\\Java2017\\????\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt";

	// ???????????? ???????????????????? ???? P8756 iPhone8
	public List<Product> getProductList() throws IOException // @02C
	{
		File file = new File(FILE_NAME);
		BufferedReader br = null;
		List<Product> prolist = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader(file));
			String temp;
			while ((temp = br.readLine()) != null) {
				String[] arrStr = temp.split(" ");
				Product p = new Product();
				p.setProductID(arrStr[0]);
				p.setProductDesc(arrStr[1]);
				prolist.add(p);
			}
			return prolist;
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}
}
