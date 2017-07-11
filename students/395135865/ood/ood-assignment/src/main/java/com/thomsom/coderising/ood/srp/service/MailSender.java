package com.thomsom.coderising.ood.srp.service;

import com.thomsom.coderising.ood.srp.Email;

import java.util.List;

/**
 * 邮件发送服务
 *
 * @author Thomson Tang
 * @version Created: 30/06/2017.
 */
public interface MailSender {

    String getSenderAddress();

    String getSmtpHost();

    void sendMail(List<Email> emails);
}
