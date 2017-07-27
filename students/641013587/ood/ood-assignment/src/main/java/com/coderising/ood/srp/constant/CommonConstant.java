package com.coderising.ood.srp.constant;

import com.coderising.ood.srp.entity.Product;
import com.coderising.ood.srp.entity.User;

public class CommonConstant {

	public static final String SUBJECT = "您关注的产品降价了";
	
	public static String getProductMessage(User user,Product product){
		return "尊敬的 "+user.getName()+", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!";
	}
	
}
