package com.coderising.ood.srp2.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.coderising.ood.srp2.model.Product;

/**
 * 文本读取工具类
 * @author mazan
 *
 */
public class FileReaderUtil {

	
	/**
	 * 读取文件内容
	 * @param is
	 * @return
	 * @throws IOException 
	 */
	public static List<Product> getTxt(InputStream is) throws IOException {
		
		List<Product> list;
		list = new ArrayList<>();
		
		InputStreamReader read;
		read = new InputStreamReader(is, "utf-8");//考虑到编码格式
		
		BufferedReader br = null;
		br = new BufferedReader(read);
		
		Product product;
		try {
			 String lineTxt = null;
             while((lineTxt = br.readLine()) != null){
//                 System.out.println(lineTxt);
                 
                 product = convertToProduct(lineTxt);
                 if (null != product) {
                	 list.add(product);
                 }
             }
		} catch (IOException e) {
			throw e;
		} finally {
			br.close();
		}
		return list;
	}
	
	private static Product convertToProduct(String txt) {
		
		String[] data = txt.split(" ");
		if (data.length != 2) {
			return null;
		}
		Product product = new Product();
		product.setProductID(data[0]);
		product.setProductDesc(data[1]);
		
		return product;
	}
	
}
