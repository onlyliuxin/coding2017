package com.coderising.ood.srp.mail;

import com.coderising.ood.srp.product.Product;
import com.coderising.ood.srp.server.SmtpMailServer;
import com.coderising.ood.srp.setting.SystemSetting;
import com.coderising.ood.srp.user.User;

import java.io.File;

/**
 * com.coderising.ood.srp.mail
 * Created by Eric Wang on 6/19/17.
 */
public class PromotionMail implements Mail {

    String form;
    String to;
    String subject;
    String message;

    SmtpMailServer smtpMailServer = new SmtpMailServer();


    private void createPriceChangeEmailBody(User user, Product product) {


            subject = "您关注的产品降价了";
            message = "尊敬的 " + user.getUserName() + ", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!";


        }

    @Override
    public void sendPriceChangeEmail(User user, Product product) {

        createPriceChangeEmailBody(user, product);
        setFrom();
        setTo(user);

        smtpMailServer.sendEmail(this);



    }

    private void setTo(User user) {
        to=user.getUserEmail();
    }

    private void setFrom() {
        form= SystemSetting.getAdmin();
    }


    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SmtpMailServer getSmtpMailServer() {
        return smtpMailServer;
    }

    public void setSmtpMailServer(SmtpMailServer smtpMailServer) {
        this.smtpMailServer = smtpMailServer;
    }
}
