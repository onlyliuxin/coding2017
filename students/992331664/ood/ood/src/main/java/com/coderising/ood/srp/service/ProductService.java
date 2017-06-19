package com.coderising.ood.srp.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.coderising.ood.srp.model.Product;

public interface ProductService {

	/**
	 * 查询促销产品
	 * @return 促销产品
	 * @throws IOException 
	 */
	List<Product> doFindPromotionalProducts(File file) throws IOException;
}
