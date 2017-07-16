package com.coderising.ood.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by john on 2017/6/14.
 */
public class BuildMail extends Build{
    List mailList;
    Product product;
    Mail mail;
    Configuration config = new Configuration();
    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";


    public BuildMail(List mailList, Product product) {
        this.mailList = mailList;
        this.product = product;
    }
    void build() {
        List list = reader.read();
        if (list != null) {
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                mail = new Mail();
                mail.setSubject(config.getProperty(ConfigurationKeys.SUBJECT));
                mail.setFromAddress(config.getProperty(ConfigurationKeys.EMAIL_ADMIN));
                try {
                    configureEMail((HashMap) iter.next());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.mailList.add(mail);
            }

        }

    }

    protected void configureEMail(HashMap userInfo) throws IOException {
        mail.setToAddress((String) userInfo.get(EMAIL_KEY));
        if (mail.getToAddress().length() > 0)
            setMessage(userInfo);
    }

    protected void setMessage(HashMap userInfo) throws IOException {

        String name = (String) userInfo.get(NAME_KEY);

        mail.setMessage("尊敬的 "+name+", 您关注的产品 " + this.product.getProductDesc() + " 降价了，欢迎购买!");


    }

}
