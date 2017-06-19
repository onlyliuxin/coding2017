package com.coderising.ood.srp.service;

import java.util.List;

import com.coderising.ood.srp.common.DBUtil;
import com.coderising.ood.srp.entity.User;

public class UserService {
	/**
	 * 获取客户列表
	 */
	public List<User> getUsers(){
		String sendMailQuery = "Select name from subscriptions "
				+ "where send_mail=1 ";
		return DBUtil.query(sendMailQuery);
	}
}
