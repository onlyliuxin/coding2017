package com.coderising.ood.srp.service.impl;

import com.coderising.ood.srp.config.Configuration;
import com.coderising.ood.srp.config.ConfigurationKeys;
import com.coderising.ood.srp.entity.Product;
import com.coderising.ood.srp.entity.User;
import com.coderising.ood.srp.service.IPromotionMail;
import com.coderising.ood.srp.service.IReadProductConfig;
import com.coderising.ood.srp.service.IUserService;
import com.coderising.ood.srp.util.MailUtil;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Enan on 17/6/18.
 */
public class PromotionMailImpl implements IPromotionMail {

    private IUserService userService;
    private IReadProductConfig readProductConfig;

    private static final String SUBJECT = "您关注的产品降价了";
    private static final String MESSAGE = "尊敬的 %s, 您关注的产品 %s 降价了，欢迎购买!";

    public PromotionMailImpl (IUserService iUserService, IReadProductConfig iReadProductConfig) {
        this.userService = iUserService;
        this.readProductConfig = iReadProductConfig;
    }

    @Override
    public void send(File file) {
        Collection<Product> products;
        try {
            products = readProductConfig.read(file);
        } catch (IOException e) {
            throw new RuntimeException("读取降价商品失败，终止发送降价邮件提醒");
        }
        for (Product product : products) {
            List<User> users = (List<User>) userService.querySubscriptedUsersByProductID(product.getProductID());

            for (User user : users) {
                try {
                    if (user.getEmail().length() > 0)
                        MailUtil.sendEmail(user.getEmail(),
                                Configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN),
                                SUBJECT,
                                String.format(MESSAGE, user.getName(), product.getProductDesc()),
                                Configuration.getProperty(ConfigurationKeys.SMTP_SERVER));
                } catch (Exception e) {
                    try {
                        MailUtil.sendEmail(user.getEmail(),
                                Configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN),
                                SUBJECT,
                                String.format(MESSAGE, user.getName(), product.getProductDesc()),
                                Configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
                    } catch (Exception e2) {
                        System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
                    }
                }
            }
        }
    }
}
