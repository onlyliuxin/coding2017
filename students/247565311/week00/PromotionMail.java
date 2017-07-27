package week00;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
class ProductMessage{
    public String productID;
    public String productDesc;
    public ProductMessage(String id,String desc){
	productID = id;
	productDesc = desc;
    }
}
class ProductMessageIter{
    BufferedReader reader = null;
    boolean loadFileSuccess = false,readFinished = false;
    public ProductMessageIter(File socFile){
        try {
    	    reader = new BufferedReader(new FileReader(socFile));
        } catch (FileNotFoundException e) {
    	    e.printStackTrace();
    	    return;
        }
        loadFileSuccess = true;
    }
    public boolean hasNext(){
	if(!loadFileSuccess || readFinished) return false;
	try {
	    reader.mark(10);
	    String lineMsg = reader.readLine();
	    reader.reset();
	    if(lineMsg.equals("")) {
		readFinished = true;
		reader.close();
		return false;
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return true;
    }
    public ProductMessage next(){
	String lineMsg=null;
	try {
	    lineMsg = reader.readLine();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	if(lineMsg == null) return null;
	String [] msgs = lineMsg.split(" ");
	ProductMessage msg = new ProductMessage(msgs[0],msgs[1]);
        System.out.println("��ƷID = " + msgs[0]);
        System.out.println("��Ʒ���� = " + msgs[1]);
	return msg;
    }
}
class EMailSender{
    String fromAddress = null;
    String smtpHost = null;
    String altSmtpHost = null; 
    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";
    
    public EMailSender(){
	Configuration config = new Configuration();
        smtpHost = config.getProperty(ConfigurationKeys.SMTP_SERVER); 
        altSmtpHost = config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);  
        fromAddress = config.getProperty(ConfigurationKeys.EMAIL_ADMIN); 
    }
    // �����ʼ����ݣ��ɹ����ɻᷢ���ʼ�
    public void configureAndSendEMail(HashMap<String,String> userInfo,ProductMessage prdMsg,boolean debug) throws IOException 
    {
	String toAddress = "",subject = "",message = "";
        toAddress = (String) userInfo.get(EMAIL_KEY); 
        if (toAddress.length() > 0) {
            String name = (String) userInfo.get(NAME_KEY);
            subject = "����ע�Ĳ�Ʒ������";
            message = "�𾴵� "+name+", ����ע�Ĳ�Ʒ " + prdMsg.productDesc + " �����ˣ���ӭ����!" ;   
            System.out.println("��ʼ�����ʼ�");
            sendEMail(toAddress,subject,message,debug);
        }else {
            System.out.println("û���ʼ�����");
        }
    }
    // ����֪ͨ�ʼ�
    void sendEMail(String toAddress,String subject,String message,boolean debug) throws IOException 
    {
        boolean sendSucceed = false;
        try 
        {
            if (toAddress.length() > 0){
                MailUtil.sendEmail(toAddress, fromAddress, subject, message, smtpHost, debug);
                sendSucceed = true;
            }
        } 
        catch (Exception e) {}
        if(!sendSucceed){
            try {
                MailUtil.sendEmail(toAddress, fromAddress, subject, message, altSmtpHost, debug); 
            } catch (Exception e2) 
            {
                System.out.println("ͨ������ SMTP�����������ʼ�ʧ��: " + e2.getMessage()); 
            }
        }
    }
}
public class PromotionMail {   
    public static void main(String[] args) throws Exception {
        File f = new File("J:\\gitstore\\coding2017\\students\\247565311\\week00\\product_promotion.txt");
        boolean emailDebug = false;
        PromotionMail pe = new PromotionMail(f, emailDebug);
    }
    // ���̿���
    public PromotionMail(File file, boolean mailDebug) throws Exception {
	ProductMessageIter iter = new ProductMessageIter(file);//��ȡ�����ļ��� �ļ���ֻ��һ���ÿո������ ���� P8756 iPhone8
	while(iter.hasNext()){
	    ProductMessage prdMsg = iter.next();
	    List<HashMap<String,String>> userInfos = loadMailingList(prdMsg);
	    for(HashMap<String,String> user : userInfos){
		new EMailSender().configureAndSendEMail(user,prdMsg,mailDebug);
	    }
	}
    }
    // ��ȡ�û������б�
    protected List<HashMap<String,String>> loadMailingList(ProductMessage prdMsg) throws Exception {
        String sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + prdMsg.productID +"' "
                + "and send_mail=1 ";
        System.out.println("�����û��б���...");
        return DBUtil.query(sendMailQuery);
    }
}
