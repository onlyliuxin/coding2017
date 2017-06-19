package main.java.com.coderising.ood.srp;

import java.util.List;

public class UserDataStore {

    public static List<UserInfo> getMailingList(String productId) throws Exception {
        final String sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + productId +"' "
                + "and send_mail=1 ";

        return DBUtil.query(sendMailQuery);
    }

}
