package com.coderising.ood.answer;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.coderising.ood.answer.entity.Product;
import com.coderising.ood.answer.service.MailService;
import com.coderising.ood.answer.utils.FileUtils;
import com.coderising.ood.answer.utils.ProductUtils;

public class Test {
	private static final Logger log = LogManager.getLogger(Test.class);
	public static void main(String[] args) {
		MailService service = new MailService();
		List<Product> list = ProductUtils.getList(FileUtils.readFile());
		service.sendMail(list);
		
	}
	
	
}
