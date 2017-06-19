package homework.jyz.coding2017;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 邮件Dao
 * Created by jyz on 2017/6/13.
 */
public class MailDao {

    public List getMails(String productID){
      String  sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + productID +"' "
                + "and send_mail=1 ";

        System.out.println("loadQuery set");
        return query(sendMailQuery);
    }

    public static List query(String sql){

        List userList = new ArrayList();
        for (int i = 1; i <= 3; i++) {
            HashMap userInfo = new HashMap<String,String>();
            userInfo.put("NAME", "User" + i);
            userInfo.put("EMAIL", "aa@bb.com");
            userList.add(userInfo);
        }

        return userList;
    }
}
