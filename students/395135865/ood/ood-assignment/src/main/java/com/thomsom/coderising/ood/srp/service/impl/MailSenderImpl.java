package com.thomsom.coderising.ood.srp.service.impl;

import com.coderising.ood.srp.Configuration;
import com.coderising.ood.srp.ConfigurationKeys;
import com.thomsom.coderising.ood.srp.Email;
import com.thomsom.coderising.ood.srp.service.MailSender;

import java.util.List;

/**
 * 发送邮件的实现类
 *
 * @author Thomson Tang
 * @version Created: 01/07/2017.
 */
public class MailSenderImpl implements MailSender {

    private Configuration configuration;

    @Override
    public String getSenderAddress() {
        return configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }

    @Override
    public String getSmtpHost() {
        return configuration.getProperty(ConfigurationKeys.SMTP_SERVER);
    }

    @Override
    public void sendMail(List<Email> emails) {
        //模拟发送邮件
        emails.forEach(email -> email.setFromAddress(getSenderAddress()));
    }
}
