package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {
	
	public static void readFile(File file,PromotionMail promotion) throws IOException // @02C
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			promotion.productID = data[0]; 
			promotion.productDesc = data[1]; 
			
			System.out.println("产品ID = " + promotion.productID + "\n");
			System.out.println("产品描述 = " + promotion.productDesc + "\n");

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}
}
