
package com.coderising.ood.util; 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.coderising.ood.model.Product;

/** 
 * @author 作者 E-mail: 
 * @version 创建时间：2017年6月17日 下午9:39:03 
 * 类说明 
 */
public class FileUtil {
	
	public static Product readProductFile(File file) throws IOException 
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			Product product=new Product();
			product.setmProductId(data[0]); 
			product.setmProductDesc(data[1]); 
			return product;
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			if(br!=null){
			br.close();
			}
		}
	}

}
 