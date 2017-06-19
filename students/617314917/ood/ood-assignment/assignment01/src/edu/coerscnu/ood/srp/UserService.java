package edu.coerscnu.ood.srp;

import java.util.HashMap;
import java.util.List;

/**
 * 用户类，获取关注降价产品的客户的名字和邮箱地址
 * 
 * @author xujie
 *
 */
public class UserService {

	protected static final String NAME_KEY = "NAME";
	protected static final String MAIL_KEY = "EMAIL";
	
	protected String query = "";

	protected void setLoadQuery(Product product) {
		query = "Select name from subscriptions " + "where product_id= '" + product.getProductID() + "' "
				+ "and send_mail=1 ";
		System.out.println("loadQuery set\n");
	}

	protected List<HashMap<String, String>> loadMailingList() throws Exception {
		return DBUtil.query(query);
	}
}
