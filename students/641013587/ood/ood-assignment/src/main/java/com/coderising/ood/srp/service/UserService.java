package com.coderising.ood.srp.service;

import java.util.List;

import com.coderising.ood.srp.dao.UserDao;
import com.coderising.ood.srp.entity.Product;
import com.coderising.ood.srp.entity.User;

public class UserService {

	private UserDao userDao= new UserDao();
	
	public List<User> getSubscribeUsers(Product product){
		return userDao.query("Select name from subscriptions where product_id= '" + product.getProductID() +"' and send_mail=1");
	}
	
}
