package main.java.com.coderising.ood.srp;

public class PromotionMail {

	private static ProductInfo productInfo = new ProductInfo();
	private static SetProtocol setprotocol = new SetProtocol();
	private static LoadInformation loadInformation = new LoadInformation();
	private static FromAddress fromAddress = new FromAddress();
	private static SendEMails sendEmails = new SendEMails();

	public static void main(String[] args) throws Exception {

		boolean emailDebug = false;
		new PromotionMail(emailDebug,
				"C:\\Users\\123\\Documents\\workspace\\ood_assignment\\src\\com\\coderising\\ood\\srp\\product_promotion.txt");
	}

	PromotionMail(boolean emailDebug, String pomotionInfoAddress) throws Exception {

		// 读取商品信息
		productInfo.readProductInfo(pomotionInfoAddress);

		// 设置发送服务协议
		setprotocol.setProtocol();

		// 设置读取信息
		loadInformation.setLoadQuery();

		// 设置发送地址
		fromAddress.setFromAddress();

		// 设置邮件内容 并 发送邮件
		sendEmails.sendEMails(emailDebug);
	}

}