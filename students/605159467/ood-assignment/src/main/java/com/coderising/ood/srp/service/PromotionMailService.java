package com.coderising.ood.srp.service;

import com.coderising.ood.srp.bean.Email;
import com.coderising.ood.srp.bean.Person;
import com.coderising.ood.srp.bean.Product;

import java.io.IOException;
import java.util.List;


public interface PromotionMailService
{







     public  List readFile(String src)  throws IOException;



    public  List querySendPerons() throws Exception;


    public String jointMessage(Person person, Product product)throws Exception;

    public void sendMessage(List<Email> emailList) throws IOException;
}
