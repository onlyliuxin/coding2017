package com.coderising.ood.srp.dao;

import java.util.List;

public interface PromotionMailDao {

	/**
	 * 读取用户信息
	 */
	List loadMailingList(String productID);

}
