package lilei.com.cn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/**
 * PromotionMail 推送邮件类  
 * 个人理解， 此类唯一能引起变化的就是发送邮件的方法变化
 * 
 * */
public class PromotionMail {

	private static MailUtil mailUtil;
	public static void main(String[] args) throws Exception {

		boolean emailDebug = false;
		PromotionMail pe = new PromotionMail(emailDebug);
	}

	public PromotionMail(boolean mailDebug) throws Exception {
		mailUtil = new MailUtil();
		mailUtil.sendEMails(mailDebug); 
	}
}
