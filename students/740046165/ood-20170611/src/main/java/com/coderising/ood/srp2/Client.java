package com.coderising.ood.srp2;

import java.util.List;
import java.util.Map;

import com.coderising.ood.srp2.util.MailUtil;
import com.coderising.ood.srp2.model.FollowUser;
import com.coderising.ood.srp2.model.Product;

public class Client {

	
	
	public static void main(String[] args) throws Exception {
		
		//获得促销降价信息
		List<Product> list = PromotionListener.getPromotionList(); 
		//没有促销信息,则不发邮件通知
		if (null == list || list.isEmpty()) {
			return;
		}
		
		/* 获取取关注用户--每一种手机的关注用户不同
		 * P8756 iPhone8	-- user1,user2
		 * P3946 XiaoMi10	-- user3,user4
		 * P8904 Oppo_R15	-- user1,user3
		 * P4955 Vivo_X20	-- user4,user5
		 */
		Map<FollowUser, List<Product>> map = SubscriptionService.getFollowUserMergeProductList(list);
		
		//发送邮件
		MailUtil.SendMail(map);
		
		
	}

}
