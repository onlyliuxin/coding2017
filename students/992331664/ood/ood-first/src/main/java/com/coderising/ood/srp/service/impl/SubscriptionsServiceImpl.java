package com.coderising.ood.srp.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.srp.model.Product;
import com.coderising.ood.srp.model.Subscriptions;
import com.coderising.ood.srp.model.User;
import com.coderising.ood.srp.service.SubscriptionsService;

public class SubscriptionsServiceImpl implements SubscriptionsService {

	@Override
	public List<Subscriptions> doFindByProducts(List<Product> products) {

		// 这里只是模拟数据
		List<Subscriptions> subscriptions = new ArrayList<Subscriptions>();
		for (int i = 0; i < 3; i++) {
			Subscriptions ss = new Subscriptions();
			ss.setUser(new User("User" + i, "aa@bb.com"));
			ss.setProduct(products.get(i));
			subscriptions.add(ss);
		}
		return subscriptions;
	}

}
