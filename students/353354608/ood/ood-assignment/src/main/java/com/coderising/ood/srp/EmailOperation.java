package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by zhenli on 17/6/11.
 */
public class EmailOperation{

    protected String toAddress = null;

    private static final String EMAIL_KEY = "EMAIL";
    private static final String NAME_KEY = "NAME";

    protected String subject = null;
    protected String message = null;


    protected void sendEMails(boolean debug, List mailingList,
                              Product product,
                              ConfigOperation configOperation) throws IOException
        {

            System.out.println("开始发送邮件");

            String fromAddress = configOperation.getFromAddress();

            String smtpHost = configOperation.getSmtpHost();

            String altSmtpHost = configOperation.getAltSmtpHost();

            if (mailingList != null) {
                Iterator iter = mailingList.iterator();
                while (iter.hasNext()) {
                    configureEMail((HashMap) iter.next(),product);
                    try
                    {
                        if (toAddress.length() > 0)
                            MailUtil.sendEmail(toAddress, fromAddress,
                                    subject, message, smtpHost, debug);
                    }
                    catch (Exception e)
                    {

                        try {
                            MailUtil.sendEmail(toAddress, fromAddress,
                                    subject, message, altSmtpHost, debug);

                        } catch (Exception e2)
                        {
                            System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
                        }
                    }
                }


            }

            else {
                System.out.println("没有邮件发送");

            }

        }

    protected void configureEMail(HashMap userInfo, Product product) throws IOException
    {
            toAddress = (String) userInfo.get(EMAIL_KEY);
            if (toAddress.length() > 0)
                setMessage(userInfo,    product);
    }

    protected void setMessage(HashMap userInfo,Product product) throws IOException
    {

        String name = (String) userInfo.get(NAME_KEY);

        subject = "您关注的产品降价了";
        message = "尊敬的 "+name+", 您关注的产品 " + product.getProductID() + " 降价了，欢迎购买!" ;



    }


}
