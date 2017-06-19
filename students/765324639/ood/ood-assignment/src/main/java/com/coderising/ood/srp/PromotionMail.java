package com.coderising.ood.srp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PromotionMail {

	public static void main(String[] args) throws Exception {

        List<Product> productList = ProductInfoReader.readProductInfo();

        for (Product product : productList) {
            List<UserInfo> userInfoList = UserInfoReader.readUserInfo(product.getId());
            List<MailInfo> mailInfoList = generateEmails(product, userInfoList);
            boolean emailDebug = false;
            MailUtil.sendEmail(mailInfoList, emailDebug);
        }

	}

    public static List<MailInfo> generateEmails(Product product, List<UserInfo> userInfoList) {
	    List<MailInfo> mailInfoList = new ArrayList<>();
	    for (UserInfo userInfo : userInfoList) {
            MailInfo mailInfo = new MailInfo();
            mailInfo.setToAddress(userInfo.getEmail());
            mailInfo.setSubject("您关注的产品降价了");
            mailInfo.setMessage("尊敬的 "+ userInfo.getUsername() +", 您关注的产品 " + product.getDesc() + " 降价了，欢迎购买!");
            mailInfoList.add(mailInfo);
        }
        return mailInfoList;
    }
}
