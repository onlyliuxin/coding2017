package test.java.org.coderising.liteaop;

import java.util.List;

public class promotionMail {
	
		public static void main(String[] args) {
			// 促销邮件
			
			// 促销产品 
			ProductUtil pp = new ProductUtil();
			Production product = pp.getPromotionalProduct();
			
			// 获得订阅人员		
			UserUtil uu = new UserUtil();
			List userlist = uu.getSubscriptionUser(product.getProductID());
			
			// 发送邮件
			EmailUtil eu = new EmailUtil();
			eu.sendEmail(userlist,product);
				
		}	
		
		
	
}
