package com.coderising.ood.srp.chasing.service;

import java.util.List;

import com.coderising.ood.srp.chasing.model.Product;
import com.coderising.ood.srp.chasing.model.User;
import com.coderising.ood.srp.chasing.util.DBUtil;

public class UserService {
	/** 获取关注指定商品的用户 */
	public List<User> loadUserByProduct(Product product){
		String sql = "Select name from subscriptions "
				+ "where product_id= '" + product.getProductID() +"' "
				+ "and send_mail=1 ";
		System.out.println("loadQuery set");
		return DBUtil.query(sql);
	}
}
