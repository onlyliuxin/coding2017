package com.coderising.ood.srp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.coderising.ood.srp.bean.Product;

/**
 * file工具类
 * <p>标题: </p>
 * <p>描述: </p>
 * @autho zx
 * @time 2017年6月13日 上午2:04:59
*/
public class FileUtil {
	
	/**
	 * 读取商品信息
	 * <p>方法名称：</p>
	 * <p>方法说明：</p>
	 * @param file
	 * @return
	 * @throws IOException
	 * @autho zx
	 * @time 2017年6月13日 上午12:39:06
	 */
	public static Product readFile(File file) throws IOException{
		BufferedReader br = null;
		Product product = new Product();
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			String productId = data[0];
			String productDesc = data[1];
			product.setProductId(productId);
			product.setProductDesc(productDesc);
			
			System.out.println("产品ID = " + productId + "\n");
			System.out.println("产品描述 = " + productDesc + "\n");
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
		return product;
	}
	
}
