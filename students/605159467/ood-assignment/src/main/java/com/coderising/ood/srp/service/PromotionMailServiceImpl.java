package com.coderising.ood.srp.service;

import com.coderising.ood.srp.bean.Email;
import com.coderising.ood.srp.bean.Person;
import com.coderising.ood.srp.bean.Product;
import com.coderising.ood.srp.dao.PromotionMailDao;
import com.coderising.ood.srp.dao.PromotionMailDaoImpl;
import com.coderising.ood.srp.utils.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created with IDEA
 * Created by fuyi.ren on 2017/6/17  15:04
 * Description:
 */
public class PromotionMailServiceImpl implements PromotionMailService
{
     private  static  PromotionMailDao promotionMailDao=new PromotionMailDaoImpl();
     private  List<Person> persons;
     private  List<Product> products;



    public void sendMessage(List<Email> emailList) throws IOException
    {
        for (Email email:emailList){
            try
            {
                email.sendMessage();
            }catch (Exception e){

                try {
                    email.standbySendMessage();
                } catch (Exception e2){
                    System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
                }

            }
        }
    }


    public List readFile(String src) throws IOException
    {
        File file=new File(src);
       return  FileUtil.readFile(file);  // 获得 产品
    }

    public List querySendPerons() throws Exception
    {
        return  promotionMailDao.loadMailingList(); //获得人员
    }


    public String jointMessage(Person person, Product product)
    {
        StringBuffer  message=new StringBuffer();
        String personName=person.getName();
        String productDesc=product.getProductDesc();
        message.append("您关注的产品降价了").append(" 尊敬的 ").append(personName)
                .append(" 您关注的产品").append(productDesc).append("降价了，欢迎购买!");
        return  message.toString();
    }
}
