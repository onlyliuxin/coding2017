package com.coderising.ood.mail;

import java.io.File;
import java.util.List;

import com.coderising.ood.model.MailSender;
import com.coderising.ood.model.MailSetting;
import com.coderising.ood.model.Product;
import com.coderising.ood.model.SubcribeMailReciver;
import com.coderising.ood.util.FileUtil;

public class PromotionMail {

	public static void main(String[] args) throws Exception {
		File file = new File("D:\\homework\\coding2017\\students\\136427763\\ood\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\config\\product_promotion.txt");
		Product product=FileUtil.readProductFile(file);
		SubcribeMailReciver subcribeMailReciver=new SubcribeMailReciver();
		List UserList=subcribeMailReciver.getMailReciverList(product);
		boolean emailDebug = false;
		MailSender mailSender=new MailSender();
		mailSender.sendEMails(emailDebug, UserList, product);
	}
}
