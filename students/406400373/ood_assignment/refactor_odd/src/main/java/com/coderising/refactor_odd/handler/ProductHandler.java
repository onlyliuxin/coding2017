package com.coderising.refactor_odd.handler;

import com.coderising.refactor_odd.entity.ProductEntity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cenkailun
 * @Date 17/6/19
 * @Time 下午9:13
 */
public class ProductHandler {

	private List<ProductEntity> products = new ArrayList<>();

	public ProductHandler() throws IOException {
		File f = new File(
				"/Users/cenkailun/codelab/coding2017/students/406400373/ood_assignment/refactor_odd/src/main/java/com/coderising/ood/srp/product_promotion.txt");
		initProducts(f);
	}

	private List<ProductEntity> initProducts(File file) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				String[] data = temp.split(" ");
				ProductEntity product = new ProductEntity();
				product.setProductId(data[0]);
				product.setProductName(data[1]);
				products.add(product);
			}
			return products;
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}

	public List<ProductEntity> fetchProducts() {
	    return products;
    }
}
