package cn.net.pikachu.srp.dao.impl;

import cn.net.pikachu.srp.dao.UserDao;
import cn.net.pikachu.srp.domain.Product;
import cn.net.pikachu.srp.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaoInMemoryImpl implements UserDao {
    private static int initalSize = 3;

    /**
     * @param product
     * @return
     */
    @Override
    public List<User> find(Product product) {
        String sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + product.getId() +"' "
                + "and send_mail=1 ";
        System.out.println("loadQuery set");

        List<User> users = new ArrayList<User>(initalSize);
        for (int i = 0; i < initalSize; i++) {
            User user = new User();
            user.setUsername("User"+i);
            user.setEmail("aa@bb.com");
            users.add(user);
        }
        return users;
    }
}
