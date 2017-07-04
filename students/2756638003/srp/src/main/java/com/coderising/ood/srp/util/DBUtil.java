package com.coderising.ood.srp.util;

import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.srp.domain.Product;
import com.coderising.ood.srp.domain.Subscriber;

public class DBUtil {

	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * 
	 * @param sql
	 * @return
	 */
	public static List<Subscriber> query(String sql, Object... args) {
		List<Subscriber> list = new ArrayList<Subscriber>(16);
		for (int i = 1; i <= 3; i++) {
			list.add(new Subscriber(String.valueOf(i), "User" + i, "user-" + i
					+ "@bb.com", new Product("P8756", " iPhone8")));
		}
		return list;
	}
}
