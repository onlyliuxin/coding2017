package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.srp.domainlogic.Product;
import com.coderising.ood.srp.domainlogic.ProductRepository;

public class ConfigProductRepository extends ProductRepository {

	private static final String PRODUCT_PROMOTION_FILE = "com/coderising/ood/srp/product_promotion.txt";
	@Override
	public List<Product> getPromotionProducts() throws IOException {
		BufferedReader br = null;
		List<Product> products = new ArrayList<>();
		try {
			String fileName = Thread.currentThread().getContextClassLoader().getResource(PRODUCT_PROMOTION_FILE)
					.getFile();
			br = new BufferedReader(new FileReader(fileName));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			Product product = new Product();
			product.setProductID(data[0]);
			product.setProductDesc(data[1]);
			products.add(product);
			System.out.println("产品ID = " + product.getProductID() + "\n");
			System.out.println("产品描述 = " + product.getProductDesc() + "\n");

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
		return products;
	}

}
