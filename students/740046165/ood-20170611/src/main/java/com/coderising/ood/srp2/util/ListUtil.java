package com.coderising.ood.srp2.util;

import java.util.List;

import com.coderising.ood.srp2.model.Product;

public class ListUtil {

	/**
	 * 产品名称
	 * @param list
	 * @return
	 */
	public static String ListToString(List<Product> list) {
		
		StringBuilder sb = new StringBuilder();
		for(Product product : list) {
			sb.append(product.getProductDesc())
				.append("/");
		}
		
		return sb.substring(0, sb.length() - 1);
	}
	
}
