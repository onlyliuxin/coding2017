package com.thomsom.coderising.ood.srp.service.impl;

import com.thomsom.coderising.ood.srp.Email;
import com.thomsom.coderising.ood.srp.Product;
import com.thomsom.coderising.ood.srp.UserInfo;
import com.thomsom.coderising.ood.srp.service.MailSender;
import com.thomsom.coderising.ood.srp.service.EmailService;
import com.thomsom.coderising.ood.srp.service.ProductService;
import com.thomsom.coderising.ood.srp.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * 邮件服务的实现类
 *
 * @author Thomson Tang
 * @version Created: 29/06/2017.
 */
public class EmailServiceImpl implements EmailService {

    private UserService userService;
    private ProductService productService;
    private MailSender mailSender;

    @Override
    public List<Email> createEmails() throws Exception {
        List<Email> emailList = new ArrayList<>();
        List<UserInfo> userInfoList = userService.listUser();
        userInfoList.forEach(userInfo -> {
            Email email = new Email();
            email.setToAddress(userInfo.getEmail());
            email.setSubject("您关注的产品降价了...");
            productService.listSubscriptProduct(userInfo.getUserId());
            email.setContent(buildContent(userInfo));
            emailList.add(email);
        });

        return emailList;
    }

    @Override
    public void sendEmails(List<Email> emails) throws Exception {
        mailSender.sendMail(emails);
    }

    private String buildContent(UserInfo userInfo) {
        List<Product> products = productService.listSubscriptProduct(userInfo.getUserId());
        StringBuilder allProduct = new StringBuilder();
        products.stream().forEach(product -> allProduct.append(product).append(","));
        return String.format("尊敬的%s，您关注的产品%s降价了，欢迎购买！", userInfo.getUserName(), allProduct.toString());
    }
}
