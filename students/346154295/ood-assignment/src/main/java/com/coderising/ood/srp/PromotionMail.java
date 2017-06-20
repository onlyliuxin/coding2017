package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PromotionMail {

	private static final String NAME_KEY = "NAME";
	private static final String EMAIL_KEY = "EMAIL";
	

	public static void main(String[] args) throws Exception {
		boolean emailDebug = false;
        File file = new File("src/main/resource/product_promotion.txt");
        //读取配置文件， 文件中只有一行用空格隔开， 例如 P8756 iPhone8
        ProductInfo productInfo = getProductInfo(file);
        List<HashMap<String, String>> mailingList = DBUtil.loadMailingList(productInfo.getProductID());
        sendEMails(emailDebug, mailingList, productInfo);

	}

	private static String getMessage(HashMap<String,String> userInfo, ProductInfo productInfo) throws IOException
	{
		String name = userInfo.get(NAME_KEY);
		return "尊敬的 "+name+", 您关注的产品 " + productInfo.getProductDesc() + " 降价了，欢迎购买!" ;
	}
	private static String getSubject() {
	    return "您关注的产品降价了";
    }

	protected static void sendEMails(boolean debug, List<HashMap<String,String>> mailingList, ProductInfo productInfo) throws IOException {
		System.out.println("开始发送邮件");
        MailUtil.init();
		if (mailingList != null) {
            for (HashMap<String, String> userInfo : mailingList) {
                String toAddress = userInfo.get(EMAIL_KEY);
                if (toAddress == null || toAddress.length() == 0) {
                    continue;
                }
                String subject = getSubject();
                String message =getMessage(userInfo, productInfo);
                MailUtil.sendEmail(toAddress, subject, message, debug);
            }

		} else {
			System.out.println("没有邮件发送");
			
		}
	}
    private static ProductInfo getProductInfo(File file) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = br.readLine();
            String[] data = temp.split(" ");
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductID(data[0]);
            productInfo.setProductDesc(data[1]);

            System.out.println("产品ID = " + productInfo.getProductID() + "\n");
            System.out.println("产品描述 = " + productInfo.getProductDesc() + "\n");
            return productInfo;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            br.close();
        }
    }
}
