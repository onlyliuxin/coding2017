package com.coding2017.practice13;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// 邮件配置职责类
public final class EmailConfiguration {

    public static final String SMTP_SERVER = "smtp.server";
    public static final String ALT_SMTP_SERVER = "alt.smtp.server";
    public static final String EMAIL_ADMIN = "email.admin";

    private static final Map<String, String> configurations = new HashMap<String, String>();

    static {
        configurations.put(SMTP_SERVER, "smtp.163.com");
        configurations.put(ALT_SMTP_SERVER, "smtp1.163.com");
        configurations.put(EMAIL_ADMIN, "admin@company.com");
    }

    // 外部不能创建实例
    private EmailConfiguration() {
    }

    // 外部不能更改配置
    public static Map<String, String> getInstance() {
        return Collections.unmodifiableMap(configurations);
    }


    public static String getProperty(String key) {
        return configurations.get(key);
    }

    public static String getFromAddress() {
        return configurations.get(EmailConfiguration.EMAIL_ADMIN);
    }

    public static String getSmtpServer() {
        return configurations.get(EmailConfiguration.SMTP_SERVER);
    }

    public static String getAltSmtpServer() {
        return configurations.get(EmailConfiguration.ALT_SMTP_SERVER);
    }
}
