package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PromotionMail {

	protected String sendMailQuery = null;

	protected String toAddress = null;

	protected String subject = null;
	protected String message = null;
	/**
	 * 1.读产品信息 2.获取发送地址 3.组织内容 4.发送
	 * 
	 * */
	// 1.获取产品信息
	protected List<Product> readFile(File file) throws IOException // @02C
	{
		List<Product> productlist = new ArrayList<Product>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp = br.readLine();
			String[] data = temp.split(" ");
			Product product = new Product();
			product.setProductID(data[0]);
			product.setProductDesc(data[1]);
			productlist.add(product);
			System.out.println("产品ID = " + data[0] + "\n");
			System.out.println("产品描述 = " + data[1] + "\n");

		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}

		return productlist;
	}

	// 获取产品信息
	public PromotionMail(File file, boolean mailDebug) throws Exception {

		// 读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		List<Product> productlist = readFile(file);
		if (productlist != null) {
			for (int i = 0; i < productlist.size(); i++) {
				Product product = (Product) productlist.get(i);
				if(product != null){
					this.consistAndSend(product);
				}
			}

		}
	}

	//获取某产品的所有订阅用户，并推送对应内容
	public void consistAndSend(Product product){
		List<User> toAddressList = product.getLoadQuery(product
				.getproductID());// 获取该产品的订阅者
		if (toAddressList != null){
			for (int j = 0; j < toAddressList.size(); j++) {
				User user = (User) toAddressList.get(j);
				Message mes = this.setMessage(user.getUserName(),
						product.getProductDesc());
				this.sendEMails(true, mes, user.getMail());
			}
		}
	}
	
	
	protected Message setMessage(String name, String productDesc){
		Message mes = new Message();
		String subject = "您关注的产品降价了";
		String message = "尊敬的 " + name + ", 您关注的产品 " + productDesc
				+ " 降价了，欢迎购买!";
		mes.setSubject(subject);
		mes.setMessageDesc(message);
		return mes;
	}

	protected void sendEMails(boolean debug, Message mes, String toAddress){
		System.out.println("开始发送邮件");
		if (null != toAddress && !toAddress.equals("")) {
			try{
			 MailUtil.sendEmail(toAddress, mes, debug);
		
			}catch(Exception e){
				
			}
		} else {
			System.out.println("没有邮件发送");

		}

	}
}
