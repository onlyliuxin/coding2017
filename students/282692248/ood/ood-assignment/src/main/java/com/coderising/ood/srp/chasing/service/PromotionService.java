package com.coderising.ood.srp.chasing.service;

import java.io.IOException;

import com.coderising.ood.srp.chasing.model.Product;
import com.coderising.ood.srp.chasing.model.User;

public class PromotionService {
	/** 促销信息概要 */
	public String getPromptProfile(){
		return "您关注的产品降价了";
	}
	
	/** 为指定用户生成促销信息 */
	public String buildPromptMessageForUser(Product product, User userInfo) throws IOException {
		return "尊敬的 "+userInfo.getName()+", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!" ;		
	}
}
