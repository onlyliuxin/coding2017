package com.coderising.ood.service;

import com.coderising.ood.pojo.Product;
import com.coderising.ood.pojo.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xyy
 * @create 2017-06-19 9:48
 **/
public class UserService {


    public static List getSendEmailUser(Product product) throws Exception {


        setLoadQuery(product);

        List<User> userList = new ArrayList();

        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setName("user" + i);
            user.setEmail(user.getName() + "@qq.com");
            userList.add(user);
        }
        return userList;
    }


    //通过产品id获取关注了产品的用户
    public static void setLoadQuery(Product product) throws Exception {

        String sql = "Select name from subscriptions "
                + "where product_id= '" + product.getProductID() + "' "
                + "and send_mail=1 ";

        System.out.println("loadQuery set");
    }


}
