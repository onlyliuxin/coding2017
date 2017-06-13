package com.coderising.ood.srp.service;

import java.util.List;

import com.coderising.ood.srp.model.Product;
import com.coderising.ood.srp.model.Subscriptions;

public interface SubscriptionsService {

	List<Subscriptions> doFindByProducts(List<Product> products);
}
