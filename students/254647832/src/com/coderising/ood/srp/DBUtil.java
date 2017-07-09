package com.coderising.ood.srp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.coderising.ood.bean.ProductBean;
import com.coderising.ood.bean.UserBean;

/**
 * <p>Title: DBUtil</p>
 * <p>Description: 数据库操作</p>
 * <p>Company: smartisan</p>
 * @author Administrator
 * @date 2017年6月18日
 */
public class DBUtil {
	
	/**
	 * 应该从数据库读， 但是简化为直接生成。
	 * 用户和关注的产品，应该是多对多的关系，应该有张多对多的表[用户id + 产品id]
	 * 先根据产品id查询出用户id，再根据用户id查询出用户信息。此处省略这部分的处理
	 * @param sql
	 * @return
	 */
	public static List<UserBean> query(Set<String> proIds){
		
		/**
		 *  SELECT name, email FROM user WHERE userid IN(
		 *  	SELECT userid FROM user_pro WHERE proId in(proIds))
		 */
		StringBuilder sendMailQuery = new StringBuilder();
		sendMailQuery.append("Select name from subscriptions where send_mail=1 and product_id in(");
		
		Iterator<String> iter = proIds.iterator();
		while(iter.hasNext()){
			sendMailQuery.append("'" + iter.next() + "',");
		}
		sendMailQuery.delete(sendMailQuery.length()-1, sendMailQuery.length()).append(")");
		
		List<ProductBean> proList = new ArrayList<ProductBean>();
		ProductBean proBean = null;
		List<UserBean> userList = new ArrayList<UserBean>();
		UserBean userInfo = null;
		for (int i = 1; i <= 3; i++) {
			userInfo = new UserBean();
			userInfo.setName("User_" + i);			
			userInfo.setEmail("aa@bb.com_" + i);
			proBean = new ProductBean();
			proBean.setProductID("pro_" + i);
			proBean.setProductDesc("proDesc_" + i);
			proList.add(proBean);
			userInfo.setPros(proList);
			userList.add(userInfo);
		}
		
		return userList;
	}
	
}
