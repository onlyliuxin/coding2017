package test.java.org.coderising.liteaop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class fileUtil {
	
	Production product;
	
	public Production  readFile (String filepath) throws IOException // @02C
	{
		File file_product = new File("E:/product_promotion.txt");
		
		BufferedReader br = null;
		try {
			
			br = new BufferedReader(new FileReader(file_product));
			
			String temp = br.readLine();
			String[] data = temp.split(" ");
			product = new Production();
			product.setProductID(data[0]); 
			product.setProductDesc(data[1]); 
			
			System.out.println("产品ID = " + data[0] + "\n");
			System.out.println("产品描述 = " + data[1] + "\n");
			
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
		
		return product;
		
	}
}
