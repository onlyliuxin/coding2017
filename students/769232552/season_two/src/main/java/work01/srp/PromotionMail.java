package work01.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {


	public static void main(String[] args) throws Exception {

		File f = new File("D:\\worksapce\\gitRepo\\coding2017\\students\\769232552\\season_two\\src\\main\\resources\\work01\\srp\\product_promotion.txt");
		boolean emailDebug = false;

		//配置邮箱
		MailBox mailBox = new MailBox();
		MailBoxConfiguration mailBoxConfiguration = new MailBoxConfiguration();
		mailBoxConfiguration.setMailBox(mailBox);
		mailBoxConfiguration.config();

		//将促销信息发送邮件
		List<Product> products = readProductFile(f);//获取促销产品信息
		for (Product product : products){
			Mail mail = new Mail(product);		//生成邮件（邮件内容，收发人信息）
			sendPromotionMails(emailDebug,mail,mailBox);
		}

	}



	public static void sendPromotionMails(boolean debug, Mail mail, MailBox mailBox) throws Exception {

		System.out.println("开始发送邮件");

		List mailingList = mail.loadMailingList(); //获取收件人列表
		if (mailingList != null) {
			Iterator iter = mailingList.iterator();
			while (iter.hasNext()) {
				mail.generateMail((HashMap) iter.next()); //生成邮件内容
				try {
					if (mail.getToAddress().length() > 0)
						mailBox.sendEmail(mail, mailBox.getSmtpHost(), debug);
				} catch (Exception e) {
					try {
						mailBox.sendEmail(mail, mailBox.getAltSmtpHost(), debug);
					} catch (Exception e2) {
						System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
					}
				}
			}

		} else {
			System.out.println("没有邮件发送");

		}
	}


	public static List<Product> readProductFile(File file) throws IOException
	{
		List<Product> list = new ArrayList<Product>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String temp;
			while ((temp = br.readLine()) != null){
				String[] data = temp.split(" ");

				Product product = new Product();
				product.setProductID(data[0]);
				product.setProductDesc(data[1]);
				list.add(product);

				System.out.println("产品ID = " + product.getProductID() + "\n");
				System.out.println("产品描述 = " + product.getProductDesc() + "\n");
			}

			return list;
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} finally {
			br.close();
		}
	}

}
