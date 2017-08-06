package com.ood.srp.mail;

import com.ood.srp.user.UserInfo;

import java.util.List;

/**
 * Created by ajaxfeng on 2017/6/20.
 */
public interface MailService {

    /**
     * 主SMTP服务器地址
     */
    public static final String SMTP_SERVER = "smtp.163.com";

    /**
     * 备用SMTP服务器地址
     */
    public static final String ALT_SMTP_SERVER = "smtp1.163.com";

    /**
     * 以哪个邮箱地址发送给用户
     */
    public static final String EMAIL_ADMIN = "admin@company.com";


    public String sendEmail(List<UserInfo> userInfoList);
}
