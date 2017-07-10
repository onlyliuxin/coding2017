package com.coderising.myood.srp;

public class PromotionMail {
	public static void main(String[] args) throws Exception {
        MailService mailService = new MailService();
        mailService.sendMails(true);
	}
}
