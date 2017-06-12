/**
 * 版权 (c) 2017 palmshe.com
 * 保留所有权利。
 */
package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
  * @Description: 加载所需要的数据，以便PromotionMail取用
  * @author palmshe
  * @date 2017年6月11日 下午10:24:46
  */
public class InitDataUtils {
	
	protected static final String GOOD_NAME= "good_name";
	protected static final String GOOD_ID= "good_id";
	
	/**
	  * @Description：生产商品
	  * @param file
	  * @return
	  * @throws IOException
	  */
	public static List generateGoods(File file) throws IOException{
		List goods= new ArrayList();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			for (int i = 0; i < 1; i++) {
				Map goodMaps= new HashMap();
				goodMaps.put(GOOD_ID, data[0]);
				goodMaps.put(GOOD_NAME, data[1]);
				goods.add(goodMaps);
			}
			
//			setProductID(data[0]); 
//			setProductDesc(data[1]); 
			
//			System.out.println("产品ID = " + productID + "\n");
//			System.out.println("产品描述 = " + productDesc + "\n");

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
		
		return goods;
	}
}
