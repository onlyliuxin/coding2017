package com.coderising.ood.srp.service;

import com.coderising.ood.srp.dao.UserDao;

import java.util.List;

/**
 * Created by justin on 17/6/19.
 */
public class UserService {

	public List querySubscriptions(String productId) {
		return new UserDao().querySubscriptions(productId);
	}
}
