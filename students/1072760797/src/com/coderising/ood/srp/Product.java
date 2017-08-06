package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Product {
	
	private File file = null;
	private	String productID;
	private String productDesc;
	public Product(){}
	public Product(File file) throws IOException{
		this.file = file;
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		readFile(file);
	}
	protected void readFile(File file) throws IOException // @02C
	{
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			setProductID(data[0]); 
			setProductDesc(data[1]); 
			
			System.out.println("产品ID = " + productID + "\n");
			System.out.println("产品描述 = " + productDesc + "\n");

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}
	
	public void setFile(File file) throws IOException {
		this.file = file;
		readFile(file);
	}
	public String getProductID() {
		if(productID.isEmpty()){
			throw new RuntimeException("no productID");
		}
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductDesc() {
		if(productDesc.isEmpty()){
			throw new RuntimeException("no productDesc");
		}
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
}
