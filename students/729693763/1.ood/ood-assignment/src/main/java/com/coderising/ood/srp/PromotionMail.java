package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PromotionMail {

	protected String subject = null;
	protected String message = null;

	private static List<String[]> products = new ArrayList<String[]>();

	private static String filePath = "/Users/vi/Desktop/ood/ood-assignment/bin/src/main/java/com/coderising/ood/srp/product_promotion.txt";
	
	private static ServerDAO server = new ServerDAO();
	
	public static void main(String[] args) throws Exception {

		File f = new File(filePath);
		boolean emailDebug = false;

		PromotionMail pe = new PromotionMail(f);
		for (String[] data : products) {
			List<Map<String, String>> list = pe.loadMailingList(data[0]);
			if (list != null && list.size() > 0) {
				pe.sendEMails(list, data[1]);
			}
		}
		
		

	}

	/**
	 * 构造器，初始化应该加载商品的详细信息。
	 * @param file
	 * @throws IOException 
	 */
	public PromotionMail(File file) throws IOException {
		//读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		readFile(file);
	}
	
//	/**
//	 * 促销邮件；
//	 * @param file
//	 * @param mailDebug
//	 * @throws Exception
//	 */
//	public PromotionMail(File file, boolean mailDebug) throws Exception {
//		
//		setLoadQuery();
//		
//		sendEMails(mailDebug, loadMailingList()); 		
//	}
//	
	
	protected void setMessage(String name, String productDesc) throws IOException {
		this.subject = "您关注的产品降价了";
		this.message = "尊敬的 " + name + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!";
	}
	
	protected void readFile(File file) throws IOException // @02C
	{
		List<String[] > list = FileUtil.parseToString(file, " ");
		if(list != null && !list.isEmpty()){
			products = list;
			for (String[] data: products) {
				System.out.println("产品ID = " + data[0] + "\n");
				System.out.println("产品描述 = " + data[1] + "\n");
			}
		}
	}

	

	protected List loadMailingList(String productID) throws Exception {
		return server.getList(productID);
	}
	
	
	protected void sendEMails(List<Map<String, String>> mailingList,String productDesc) throws IOException 
	{
		System.out.println("开始发送邮件");
	
		if (mailingList != null) {
			Iterator<Map<String, String>> iter = mailingList.iterator();
			while (iter.hasNext()) {
				Map<String, String> map = iter.next();
				String toAddress = (String) map.get("EMAIL");
				String name = (String) map.get("NAME");
				setMessage(name, productDesc);
				if (toAddress != null && toAddress.length() > 0)
					MailUtil.sendEmail(toAddress, subject, message);
			}
		} else {
			System.out.println("没有邮件发送");
		}
	}
}
