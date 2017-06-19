package com.coderising.ood.srp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.coderising.ood.srp.entity.ProductInfo;

public class FileUtil {
	
	public static void readFileAndSetProductInfo(File file,ProductInfo productInfo) throws IOException // @02C
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			productInfo.setProductID( data[0]);
			productInfo.setProductDesc( data[1]);
			
			
			
			System.out.println("产品ID = " + data[0] + "\n");
			System.out.println("产品描述 = " + data[1] + "\n");

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}

}
