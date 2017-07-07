/**
 * 版权 (c) 2017 palmshe.com
 * 保留所有权利。
 */
package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @Description: 
  * @author palmshe
  * @date 2017年6月11日 下午10:24:46
  */
public class DataGenerator {

	/**
	  * @Description：获取商品
	  * @param file
	  * @return
	  * @throws IOException
	  */
	public static Map generateGoods(File file) throws IOException{
		Map good= new HashMap();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			good.put(PromotionMail.ID_KEY, data[0]);
			good.put(PromotionMail.DESC_KEY, data[1]);
			
			System.out.println("产品ID = " + data[0] + "\n");
			System.out.println("产品描述 = " + data[1] + "\n");

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
		return good;
	}
	
	/**
	  * @Description：获取客户
	  * @param good
	  * @return
	  * @throws Exception
	  */
	public static List loadMailingList(Map good) throws Exception {
		String id= (String)good.get(PromotionMail.ID_KEY);
		String sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + id +"' "
				+ "and send_mail=1 ";
		
		return DBUtil.query(sendMailQuery);
	}
}
