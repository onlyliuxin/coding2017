package com.coderising.ood.srp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.coderising.ood.srp.model.Product;
import com.coderising.ood.srp.model.Subscriptions;
import com.coderising.ood.srp.service.SubscriptionsService;
import com.coderising.ood.srp.util.DBUtil;

public class SubscriptionsServiceImpl implements SubscriptionsService {

	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";

	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public List<Subscriptions> doFindByProducts(List<Product> products) {

		List<String> productIds = products.stream().map(Product::getProductID).collect(Collectors.toList());
		String sendMailQuery = "Select name from subscriptions where product_id in( productIds ) and send_mail = 1 ";
		List list = DBUtil.query(sendMailQuery);

		// 这里只是模拟数据
		List<Subscriptions> subscriptions = new ArrayList<Subscriptions>();
		for (int i = 0; i < list.size(); i++) {
			HashMap userInfo = (HashMap) list.get(i);
			Subscriptions ss = new Subscriptions();
			ss.setName((String) userInfo.get(NAME_KEY));
			ss.setEmail((String) userInfo.get(EMAIL_KEY));
			ss.setProduct(products.get(i));
			subscriptions.add(ss);
		}

		return subscriptions;
	}

}
