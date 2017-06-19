package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileUtil {

	public static Map<String, String> readFile(String filePath) throws IOException {
		File file = new File(filePath);
		BufferedReader br = null;
		Map<String, String> productMap = new HashMap<>();
		try {
			br = new BufferedReader(new FileReader(file));
			String content;
			while((content = br.readLine()) != null) {
				String[] data = content.split(" ");
				String productId = data[0];
				String productDesc = data[1];
				productMap.put(productId, productDesc);
				System.out.println("产品ID = " + productId + "\n");
				System.out.println("产品描述 = " + productDesc + "\n");
			}

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}

		return productMap;
	}
}
