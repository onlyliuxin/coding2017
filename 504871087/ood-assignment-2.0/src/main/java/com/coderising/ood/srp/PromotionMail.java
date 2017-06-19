package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.security.KeyStore.PrivateKeyEntry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Model.Message;
import Model.ProductInfo;
import Model.UserInfo;
import configurations.Configuration;
import utils.DBUtil;

public class PromotionMail {

	private static Configuration config; 
	private static Deliver deliver;
	private static Message message;
	
	private ProductInfo productInfo;
	private List<UserInfo> users;
	private String sendMailQuery = null;
	private String filePath = "/Users/Art1st/Documents/git.code/coding2017/liuxin/ood/ood-assignment/src/main/java/com/coderising/ood/srp/product_promotion.txt";

	public static void main(String[] args) throws Exception {
		boolean emailDebug = false;
		PromotionMail pe = new PromotionMail(emailDebug);
	}

	
	public PromotionMail(boolean mailDebug) throws Exception {
		init();
		for (UserInfo userInfo : users) {
			message.setMessage(userInfo, productInfo);
			deliver.sendMails(message, mailDebug);
		}
	}

	/*
	 * 初始化邮件信息
	 */
	private void init() throws Exception {
		File file = new File(filePath);
		productInfo = getProductInfo(file);
		config = new Configuration();
		setLoadQuery();
		users = loadMailingList();
		deliver = new Deliver();
		message = new Message();
	}

	public ProductInfo getProductInfo(File file) throws IOException {
		ProductInfo productInfo = new ProductInfo();
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			
			productInfo.setProductID(data[0]);
			productInfo.setProductDesc(data[1]);
			
			System.out.println("产品ID = " + data[0] + "\n");
			System.out.println("产品描述 = " + data[1] + "\n");
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
		return productInfo;
	}

	protected void setLoadQuery() throws Exception {
		
		sendMailQuery = "Select name from subscriptions "
				+ "where product_id= '" + productInfo.getProductID() +"' "
				+ "and send_mail=1 ";
		
		System.out.println("loadQuery set");
	}

	protected List loadMailingList() throws Exception {
		return DBUtil.query(this.sendMailQuery);
	}
	
}
