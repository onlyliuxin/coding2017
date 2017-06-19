package com.coderising.ood.srp;

import java.util.HashMap;
import java.util.Map;

public class Configuration {

    static Map<String, String> configurations = new HashMap();
    static final String QUERY = "Select name from subscriptions "
            + "where product_id= '" + "%s" +"' "
            + "and send_mail=1 ";

    static {
        configurations.put(ConfigurationKeys.SMTP_SERVER, "smtp.163.com");
        configurations.put(ConfigurationKeys.ALT_SMTP_SERVER, "smtp1.163.com");
        configurations.put(ConfigurationKeys.EMAIL_ADMIN, "admin@company.com");
        configurations.put(ConfigurationKeys.SEND_MAIL_QUERY, QUERY);
        configurations.put(ConfigurationKeys.SUBJECT, "您关注的产品降价了");

    }

    /**
     * 应该从配置文件读， 但是这里简化为直接从一个map 中去读
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {

        return configurations.get(key);
    }


}
