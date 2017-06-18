package com.coderising.ood.srp;

import java.util.List;
import java.util.Map;

public interface UserInfoService {
	/**
	 * 根据产品id获取订阅信息用户
	 * @param productID
	 * @return
	 */
	public List<Map<String,String>> getList(String productID);
	
}
