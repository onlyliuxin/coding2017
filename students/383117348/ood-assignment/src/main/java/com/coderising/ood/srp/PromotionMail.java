package com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PromotionMail {

	protected String subject = null;
	protected String message = null;

	private static List<String[]> products = new ArrayList<String[]>();

	private static String filePath = "E:\\coding2017\\students\\383117348\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt";

	private UserInfoService uis = new UserInfoServiceImpl();

	public PromotionMail(File file) throws Exception {
		// 读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
		readFile(file);
	}

	public static void main(String[] args) throws Exception {
		File f = FileUtil.readFile(filePath);
		PromotionMail pe = new PromotionMail(f);
		// 遍历每条产品信息
		for (String[] data : products) {
			List<Map<String, String>> list = pe.loadUserInfoList(data[0]);
			if (list != null && list.size() > 0) {
				pe.sendEMails(list, data[1]);
			}
		}
	}

	/**
	 * 读取产品文件,获得所有的产品信息
	 * 
	 * @param file
	 */
	protected void readFile(File file) {
		List<String[]> datas = FileUtil.parseToString(file, " ");
		if (datas != null && datas.size() > 0) {
			products = datas;
			for (String[] data : products) {
				System.out.print("产品ID = " + data[0] + "\n");
				System.out.println("产品描述 = " + data[1] + "\n");
			}
		}
	}

	/**
	 * 根据产品id获取需要发送的用户信息,暂未考虑sql注入的安全问题
	 * 
	 * @return
	 * @throws Exception
	 */
	protected List<Map<String, String>> loadUserInfoList(String productID) throws Exception {
		return uis.getList(productID);
	}

	/**
	 * 设置发送的消息体
	 * 
	 * @param name
	 * @throws IOException
	 */
	protected void setMessage(String name, String productDesc) throws IOException {
		this.subject = "您关注的产品降价了";
		this.message = "尊敬的 " + name + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!";
	}

	/**
	 * 发送邮件
	 * 
	 * @param mailingList
	 * @throws IOException
	 */
	protected void sendEMails(List<Map<String, String>> mailingList, String productDesc) throws IOException {

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
