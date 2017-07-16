package com.coderising.ood.srp_restructure_1.service;

import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.srp_restructure_1.pojo.Product;
import com.coderising.ood.srp_restructure_1.pojo.User;
import com.coderising.ood.srp_restructure_1.util.DBUtil;

public class UserService {

	public List<User> getSendMailUser(Product product) {
		String sql = "Select name from subscriptions " + "where product_id= '" + product.getProductID() + "' "
				+ "and send_mail=1 ";
		return DBUtil.query(sql);
	}

}
