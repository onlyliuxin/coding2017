package com.coderising.ood.srp;
import java.io.File;
import java.util.List;

public class PromotionMail {


	public static void main(String[] args) throws Exception {

		PromotionMail pe = new PromotionMail();

	}


	public PromotionMail() throws Exception {

		if (Configuration.MAIL_DEBUG) {
			System.out.println("debugging...");
		}

		ProductFactory factory = new ProductFactory();

		File file = new File(Configuration.getProperty(ConfigurationKeys.FILE_PATH));
        List<Product> products = factory.getNewProdcuts(file);

        String productID = "1";
        String sql = "Select name from subscriptions "
				+ "where product_id= '" + productID +"' "
				+ "and send_mail=1 ";

        List<User> users = DBUtil.query(sql);


		for (User user : users) {
			for (Product product : products) {

				String to_address = user.getEmail();
				String from_address = Configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN);
				String subject = "您关注的产品降价了";
				String message = "尊敬的 "+ user.getName() + ", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!" ;

				Message msg = new Message(to_address, from_address, subject, message);

				MailUtil.sendEmail(msg);
			}

		}
	}


}
