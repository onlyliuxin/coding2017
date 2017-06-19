package main.java.com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProductInfo {

	private String productID = null;
	private String productDesc = null;

	/**
	 * 
	 * @throws IOException
	 */
	public void readProductInfo(String pomotionInfoAddress) throws IOException {

		File f = new File(pomotionInfoAddress);
		
		readFile(f);
	}

	private void readFile(File file) throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");

			productID = data[0];
			productDesc = data[1];

			System.out.println("产品ID = " + productID + "\n");
			System.out.println("产品描述 = " + productDesc + "\n");

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}

	protected String getproductID() {
		return productID;
	}

	protected String getProductDesc() {
		return productDesc;
	}

}
