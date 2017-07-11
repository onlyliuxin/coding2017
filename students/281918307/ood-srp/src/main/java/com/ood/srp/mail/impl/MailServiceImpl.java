package com.ood.srp.mail.impl;

import com.ood.srp.mail.MailService;
import com.ood.srp.user.UserInfo;
import com.ood.srp.util.MailUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yuxia on 2017/6/21.
 */
@Service
public class MailServiceImpl implements MailService {

    /**
     * 发送邮件
     *
     * @param userInfoList
     * @return
     */
    @Override
    public String sendEmail(List<UserInfo> userInfoList) {
        if (userInfoList == null) {
            System.out.println("没有邮件发送");
            return "none";
        }

        for (UserInfo info : userInfoList) {
            if (!info.getEmail().isEmpty()) {
                String emailInfo = generatePromotionEmail(info);
                try {
                    MailUtil.sendPromotionEmail(emailInfo);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return "success";
    }

    /**
     * 根据用户信息生成促销邮件内容。
     *
     * @param userInfo 用户信息。
     * @return 返回生成的邮件。
     */
    private static String generatePromotionEmail(UserInfo userInfo) {
        StringBuilder buffer = new StringBuilder();

        buffer.append("From:").append(EMAIL_ADMIN).append("\n");
        buffer.append("To:").append(userInfo.getEmail()).append("\n");
        buffer.append("Subject:").append("您关注的产品降价了").append("\n");
        buffer.append("Content:").append("尊敬的").append(userInfo.getName());
        buffer.append(", 您关注的产品 ").append(userInfo.getProductDesc());
        buffer.append(" 降价了，欢迎购买!").append("\n");

        System.out.println(buffer.toString());

        return buffer.toString();
    }
}
