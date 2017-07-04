package com.coderising.ood.srp.util;

import java.util.List;

import com.coderising.ood.srp.domain.Subscriber;

/**
 * 订阅者查询工具类
 */
public class SubscriberUtil {

	public static List<Subscriber> loadSubscriberList(String sql,
			Object... args) {
		return DBUtil.query(sql);
	}

}
