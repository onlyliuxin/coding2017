package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * 这个类的作用只是用于获取资源文件中的降价商品
 * @author gaohuan
 *
 */
public class Goods {
	private String productID;
	private String productDesc;
	
	/**
	 * 获得降价
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("finally")
	public ArrayList<Goods>  getSaleGoods() throws IOException {
		ArrayList<Goods> goodsList = new ArrayList<Goods>();
		File file = new File("src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			Goods goods = new Goods(data[0], data[1]);
			goodsList.add(goods);
			System.out.println("产品ID = " + goods.getProductID() + "\n");
			System.out.println("产品描述 = " + goods.getProductDesc() + "\n");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
			return goodsList;
		}
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Goods() {
		super();
	}

	public Goods(String productID, String productDesc) {
		super();
		this.productID = productID;
		this.productDesc = productDesc;
	}
	
	
	
	
	
	
	
	
	
	
}
