package com.mimieye.odd.srp.controller;

import java.io.IOException;
import java.util.HashMap;

public class PromotionMail extends PromotionAbstractMail{

    /**
     * 自定义邮件内容
     * @param userInfo
     * @throws IOException
     */
    protected void setMessage(HashMap userInfo) throws IOException {
        String name = (String) userInfo.get(NAME_KEY);
        subject = "您关注的产品降价了";
        message = "尊敬的 " + name + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!";
    }

}
