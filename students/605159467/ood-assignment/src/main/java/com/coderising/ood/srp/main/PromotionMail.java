package com.coderising.ood.srp.main;

import com.coderising.ood.srp.bean.Email;
import com.coderising.ood.srp.bean.Person;
import com.coderising.ood.srp.bean.Product;
import com.coderising.ood.srp.resource.ConfigurationKeys;
import com.coderising.ood.srp.service.PromotionMailService;
import com.coderising.ood.srp.service.PromotionMailServiceImpl;

import javax.xml.ws.Service;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PromotionMail
{

   public  static 	PromotionMailService mailService = new PromotionMailServiceImpl();
	public static void main(String[] args) throws Exception
	{

		String src = mailService.getClass().getResource("../resource") + "/product_promotion.txt";
		List<Product> productList = mailService.readFile(src);
		List<Person> personList = mailService.querySendPerons();

        List<Email>  emailList=getEmailList(productList,personList);

        mailService.sendMessage(emailList);
	}

	private static List getEmailList( List<Product> productList, List<Person> personList) throws Exception
	{
		List  emailList=new ArrayList<Email>();
		for (Person person : personList)
		{
			for (Product product : productList)
			{
				Email email = new Email();
				String message=mailService.jointMessage(person, product);
				email.setToAddress(person.getEmail());
				email.setMessage(message);
				emailList.add(email);
			}
		}
		return  emailList;
	}


}