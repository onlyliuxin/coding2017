package com.thomsom.coderising.ood.srp.service.impl;

import com.thomsom.coderising.ood.srp.Email;
import com.thomsom.coderising.ood.srp.Product;
import com.thomsom.coderising.ood.srp.UserInfo;
import com.thomsom.coderising.ood.srp.service.EmailService;
import com.thomsom.coderising.ood.srp.service.UserService;

import java.util.List;

/**
 * 类说明
 *
 * @author Thomson Tang
 * @version Created: 29/06/2017.
 */
public class EmailServiceImpl implements EmailService {

    private UserService userService;

    @Override
    public Email newEmail() throws Exception {
        List<UserInfo> userInfoList = userService.listUser();
        for (UserInfo userInfo : userInfoList) {
            Email email = new Email();
            email.setToAddress(userInfo.getEmail());
            email.setSubject("您关注的产品降价了...");
            email.setContent("");
        }

        return null;
    }

    private String generateContent(UserInfo userInfo, Product product) {
        return String.format("尊敬的%s，您关注的产品%s降价了，欢迎购买！", userInfo.getUserName(), product.getProductName());
    }
}
