package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class ProductUtil {
	/**
	 * 得到促销产品列表,至于从哪里读取，由该方法内部决定，外部不需要知道
	 * @throws IOException 
	 */
	public static List<Product> getProducts() throws IOException{
		return readFile(new File(Configuration.PRODUCTS_FILE_PATH));
	}

	private static List<Product> readFile(File file) throws IOException {
		List<Product> products = new ArrayList<Product>();
		FileInputStream fis = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
		for( String line = br.readLine(); line != null; line = br.readLine() ){
			Product product = new Product(line.split(" ")[0], line.split(" ")[1]);
			products.add(product);
		}
		
		return products;
		
	}
		
}	
