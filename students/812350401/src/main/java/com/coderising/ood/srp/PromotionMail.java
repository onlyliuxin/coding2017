package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail {
	public static void main(String[] args) throws Exception {
        MailService mailService = new MailService();
        mailService.sendMails(true);
	}
}
