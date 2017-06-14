package com.coderising.ood.srp;

import java.io.File;

import com.coderising.ood.srp.service.PromotionMailService;
import com.coderising.ood.srp.service.impl.PromotionMailServiceImpl;


public class PromotionMail {


	public static void main(String[] args) throws Exception {
		
		File f = new File("D:\\Program Files\\mygit\\coding2017\\students\\335402763\\src\\main\\java\\com\\coderising\\ood\\srp\\product_promotion.txt");
		boolean emailDebug = false;
		
		PromotionMailService promotionMailService = new PromotionMailServiceImpl();
		promotionMailService.sendPromotionMail(f, emailDebug);
	}
	
}
