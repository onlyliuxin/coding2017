package com.coderising.ood.srp.dao.impl;

import java.util.List;

import com.coderising.ood.srp.dao.PromotionMailDao;
import com.coderising.ood.srp.utils.DBUtil;

public class PromotionMailDaoImpl implements PromotionMailDao {

	protected String productID = null;
	protected String sendMailQuery = null;
	
	/**
	 * 读取用户信息
	 */
	@Override
	public List loadMailingList(String productID) {
		try {
			setLoadQuery(productID);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DBUtil.query(this.sendMailQuery);

	}

	protected void setLoadQuery(String productID) throws Exception {

		sendMailQuery = "Select name from subscriptions " + "where product_id= '" + productID + "' "
				+ "and send_mail=1 ";

		System.out.println("loadQuery set");
	}
}
