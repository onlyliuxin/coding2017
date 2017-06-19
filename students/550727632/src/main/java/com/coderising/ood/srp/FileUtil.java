package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

	public static Product readProductFile(File file) throws IOException {
		BufferedReader br = null;
		Product product = new Product();
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			product.setProductID(data[0]);
			product.setProductDesc(data[1]);
			System.out.println(product.toString());

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
		return product;
	}
}
