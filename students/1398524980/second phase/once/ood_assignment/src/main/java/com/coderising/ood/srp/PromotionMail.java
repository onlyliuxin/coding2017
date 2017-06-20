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

		// ��ȡ��Ʒ��Ϣ
		productInfo.readProductInfo(pomotionInfoAddress);

		// ���÷��ͷ���Э��
		setprotocol.setProtocol();

		// ���ö�ȡ��Ϣ
		loadInformation.setLoadQuery();

		// ���÷��͵�ַ
		fromAddress.setFromAddress();

		// �����ʼ����� �� �����ʼ�
		sendEmails.sendEMails(emailDebug);
	}

}