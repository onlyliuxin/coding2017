package ood.srp;

import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    /**
     * 应该从数据库读， 但是简化为直接生成。
     * @param sql
     * @return
     */
    public static List<User> query(String sql){
        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            User user = new User();
            user.setName("User" + i);
            user.setEmail("aa@bb.com");
            userList.add(user);
        }
        return userList;
    }

    public static List<User> queryValidUserList(String sql) {
        List<User> userList = query(sql);
        List<User> resultList = new ArrayList<>();
        for (int i = 0, len = userList.size(); i < len; i++) {
            String email = userList.get(i).getEmail();
            if (email != null && email.length() > 0) {
                resultList.add(userList.get(i));
            }
        }
        return resultList;
    }

    public static List<User> queryUserListByProduct(Product product) {
        String sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + product.getProductID() + "' "
                + "and send_mail=1 ";
        return queryValidUserList(sendMailQuery);
    }

}
