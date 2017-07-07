package com.coderising.ood.srp.jdbc;

import java.util.List;

import com.coderising.ood.srp.util.DBUtil;

/**
 * 用户 jdbc
 * <p>标题: </p>
 * <p>描述: </p>
 * @autho zx
 * @time 2017年6月13日 上午12:51:00
*/
public class UserJDBC {
	
	/**
	 * 根据商品Id 获取用户信息
	 * <p>方法名称：</p>
	 * <p>方法说明：</p>
	 * @param productId
	 * @return
	 * @autho zx
	 * @time 2017年6月13日 上午12:51:14
	 */
	public List selectUserId(String sql){
		return DBUtil.query(sql);
	}
	
}
