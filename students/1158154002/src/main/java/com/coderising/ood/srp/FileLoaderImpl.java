package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileLoaderImpl extends BaseFileLoader{

	@Override
	public  Map readFile(File file) {
		BufferedReader br = null;
		Map map=new HashMap<String,String>();
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			map.put("productID", data[0]);
			map.put("productDesc", data[1]);
			
			System.out.println("产品ID = " + data[0] + "\n");
			System.out.println("产品描述 = " + data[1] + "\n");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}

}
