package com.thomsom.coderising.ood.srp;

import com.thomsom.coderising.ood.srp.service.EmailService;
import com.thomsom.coderising.ood.srp.service.impl.EmailServiceImpl;

import java.util.List;

/**
 * 应用场景类: 促销任务
 *
 * @author Thomson Tang
 * @version Created: 02/07/2017.
 */
public class PromotionTask {
    public static void main(String[] args) {
        try {
            EmailService emailService = new EmailServiceImpl();
            List<Email> emails = emailService.createEmails();
            emailService.sendEmails(emails);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
