package com.coderising.ood.answer.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.coderising.ood.answer.entity.Product;

/**
 * 产品工具
 * @author readke
 *
 */
public class ProductUtils {
	private static final Logger log = LogManager.getLogger(ProductUtils.class);
	
	public static List<Product> getList(File file){
		List<Product> list = null;
		BufferedReader br = null;
		
		try {
			list = new ArrayList<>();
			br = new BufferedReader(new FileReader(file));
			while(br.ready()){
				Product p = new Product();
				String temp = br.readLine();
				String[] data = temp.split(" ");
				p.setpId(data[0]);
				p.setpDec(data[1]);
				list.add(p);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
}
