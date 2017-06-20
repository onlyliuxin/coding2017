package com.coderising.ood.model;

import java.util.List;

import com.coderising.ood.util.DBUtil;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年6月17日 下午10:51:43 类说明
 */
public class SubcribeMailReciver {

	private String sendMailQuery;
	
	private void setLoadQuery(Product product) throws Exception {

		sendMailQuery = "Select name from subscriptions " + "where product_id= '" + product
				.getmProductId() + "' " + "and send_mail=1 ";
		System.out.println("loadQuery set");
	}

	public List getMailReciverList(Product product) throws Exception {
		setLoadQuery(product);
		return DBUtil.query(this.sendMailQuery);
	}
	
}
