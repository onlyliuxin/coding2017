package com.coderising.ood.srp.service;

import java.util.List;

import com.coderising.ood.srp.model.Product;
import com.coderising.ood.srp.model.Subscriptions;

public interface SubscriptionsService {

	/**
	 * 查询产品的订阅人
	 * 
	 * @param products
	 *            产品
	 * @return 订阅信息
	 */
	List<Subscriptions> doFindByProducts(List<Product> products);
}
