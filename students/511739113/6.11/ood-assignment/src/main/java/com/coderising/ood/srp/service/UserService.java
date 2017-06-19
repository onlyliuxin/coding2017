package com.coderising.ood.srp.service;

import java.util.List;

import com.coderising.ood.srp.jdbc.UserJDBC;

/**
 * 用户 service
 * <p>标题: </p>
 * <p>描述: </p>
 * @autho zx
 * @time 2017年6月13日 上午12:53:30
*/
public class UserService {
	
	/** 模拟注入userJDBC */
	private UserJDBC userJDBC = new UserJDBC();
	
	/**
	 * 获取用户信息
	 * <p>方法名称：</p>
	 * <p>方法说明：</p>
	 * @param productId
	 * @return
	 * @autho zx
	 * @time 2017年6月13日 上午12:56:45
	 */
	public List queryUserInfo(String productId){
		String sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + productId +"' "
				+ "and send_mail=1 ";
		return userJDBC.selectUserId(sendMailQuery);
	}
	
}
