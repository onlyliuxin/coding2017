package com.thomsom.coderising.ood.srp.service;

import com.thomsom.coderising.ood.srp.Email;

import java.util.List;

/**
 * 邮件服务
 *
 * @author Thomson Tang
 * @version Created: 29/06/2017.
 */
public interface EmailService {
    /**
     * 创建要发送的邮件，相当于写邮件
     *
     * @return 返回若干邮件
     * @throws Exception if error
     */
    List<Email> createEmails() throws Exception;

    /**
     * 发送邮件
     * @param emails 要发送的邮件
     * @throws Exception if error occurs
     */
    void sendEmails(List<Email> emails) throws Exception;
}
