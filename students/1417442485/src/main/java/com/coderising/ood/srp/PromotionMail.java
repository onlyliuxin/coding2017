package main.java.com.coderising.ood.srp;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PromotionMail {

	private static final String PRODUCT_FILE = "src/main/java/com/coderising/ood/srp/product_promotion.txt";
	
	public static void main(String[] args) throws Exception {

		final Product product = new ProductDataStore(new File(PRODUCT_FILE)).getProduct();
        final List<UserInfo> userList = UserDataStore.getMailingList(product.productId);
        final MailSetting mailSetting = new MailSetting(false);

		PromotionMail.sendEmails(product, userList, mailSetting);
	}

	protected static void sendEmails(final Product product, final List<UserInfo> userList, final MailSetting mailSetting) throws IOException
	{
        if(userList == null || userList.isEmpty()) {
            System.out.println("没有邮件发送");
            return;
        }
        System.out.println("开始发送邮件");
        for (UserInfo userInfo : userList) {
            final PromotionMailMessage message = new PromotionMailMessage(userInfo.email, userInfo.name, product.productDesc);
            MailService.send(message, mailSetting);
        }
	}
}
