package com.mimieye.odd.srp.main;

import com.mimieye.odd.srp.controller.PromotionAbstractMail;
import com.mimieye.odd.srp.controller.PromotionMail;

/**
 * Created by Pierreluo on 2017/6/17.
 */
public class PromotionEmailMain {
    public static void main(String[] args) throws Exception {
        PromotionAbstractMail mail = new PromotionMail();
        // 初始化数据
        mail.init();
        // 发送邮件
        mail.send();
    }
}
