package com.coderising.ood.srp.service;

import com.coderising.ood.srp.interfaces.SendMailFunction;
import com.coderising.ood.srp.model.Mail;
import com.coderising.ood.srp.model.Product;
import com.coderising.ood.srp.model.User;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Iden on 2017/6/14.
 */
public class SendPriceMail implements SendMailFunction {


    @Override
    public void sendMail(List<Product> products) {
        if (null == products || products.size() == 0) {
            System.out.println("没有发现需要促销的产品");
            return;
        }
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            List<User> userList = product.getSubscribers();
            if (null == userList || userList.size() == 0) {
                System.out.println("没有人订阅 " + product.getDescription() + " 信息");
                continue;
            }
            Iterator iter = userList.iterator();
            while (iter.hasNext()) {
                User user = (User) iter.next();
                Mail mail = new Mail();
                mail.setSubject("您关注的产品降价了");
                mail.setContent("尊敬的 " + user.getName() + ", 您关注的产品 " + product.getDescription() + " 降价了，欢迎购买");
                mail.setToAddress(user.geteMail());
                mail.send();
            }
        }
    }
}
