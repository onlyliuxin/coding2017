/*
 * SubscriptionService.java Created On 2017年6月21日
 * Copyright(c) 2017 Mazan Inc.
 * ALL Rights Reserved.
 */
package com.coderising.ood.srp2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coderising.ood.srp2.model.FollowUser;
import com.coderising.ood.srp2.model.Product;
import com.coderising.ood.srp2.util.DBUtil;

/**
 * SubscriptionService
 * 订阅信息Service
 * @time: 下午3:36:58
 * @author mazan
 */
public class SubscriptionService {
	
	/*
	 * DB订阅表结构
	 * | id | userId | productId|
	 * -------------------------->
	 * | userName | productDesc|
	 * |----------|------------|
	 * | user1 	  | iphone8	   |
	 * | user1 	  | Oppo_R15   |
	 * | user2 	  | iphone8    |
	 * | user3 	  | Oppo_R15   |
	 * | user3 	  | XiaoMi10   |
	 * | user4 	  | XiaoMi10   |
	 * | user4 	  | Vivo_X20   |
	 * | user5 	  | Vivo_X20   |
	 * 
	 */
	
	/**
	 * 根据产品查找其订阅的用户
	 * 
	 * @param list
	 * @return
	 */
	public static List<FollowUser> getFollowUserByProduct(Product product) {
		
		return DBUtil.query(product.getProductID());
	}
	/**
	 * 根据用户查找其订阅的产品
	 * @param user
	 * @return
	 */
	public static List<Product> getProductListByFollowUser(FollowUser user) {
		
		return null;
	}
	
	/**
	 * 对于每一种手机产品，查找其对应的订阅用户
	 * @param list
	 * @return
	 */
	public static Map<Product, List<FollowUser>> getFollowUserByProductList(List<Product> list) {
		
		Map<Product, List<FollowUser>> map = new HashMap<>();
		List<FollowUser> userList = null;
		for (Product product : list) {
			userList = getFollowUserByProduct(product);
			if (null != list && !list.isEmpty()) {
				map.put(product, userList);
			}
			
		}
		
		//打印map1
//		System.out.println("-------------------------map1-------------------------------");
//		for (Map.Entry<Product, List<FollowUser>> entry : map.entrySet()) {
//			System.out.println("key = " + entry.getKey().getProductDesc());
//			System.out.println("value = " + entry.getValue().size());
//		}
		
		
		return map;
	}
	
	/**
	 * 合并订阅用户 -- 只发一封邮件
	 * 
	 * @param map
	 * @return
	 */
	public static Map<FollowUser, List<Product>> getFollowUserMergeProductList(List<Product> list) {
		
		return merge(getFollowUserByProductList(list));
	}
	
	/**
	 * map util类
	 * @param map1
	 * --> 可以抽取成一个PO
	 * --> 或者类似于Hibernate一样的添加为User的属性
	 * @return
	 */
	private static Map<FollowUser, List<Product>> merge(Map<Product, List<FollowUser>> map1) {
		
		Map<FollowUser, List<Product>> map2 = new HashMap<>();
		
		List<Product> plist = null;
		
		for (Map.Entry<Product, List<FollowUser>> entry : map1.entrySet()) {
			Product key = entry.getKey();
			List<FollowUser> value = entry.getValue();
			for (FollowUser user : value) {
				
				//不能用对象--hashcode不一致 ==>>重写hashCode和equals方法,或者用户单例
				//实际使用中仅保证id一致即可
				plist = map2.get(user);
				
				if (null == plist) {
					plist = new ArrayList<>();
				} 
				plist.add(key); //把当前key加入到plist
				map2.put(user, plist);
			}
			
		}
		
		//打印map2
//		System.out.println("-------------------------map2-------------------------------");
//		for (Map.Entry<FollowUser, List<Product>> entry : map2.entrySet()) {
//			System.out.println("key = " + entry.getKey().getUserName());
//			System.out.println("value = " + entry.getValue().size());
//		}
		
		
		return map2;
		
	}
	
	
	
}

