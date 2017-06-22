package ood.srp;

import java.util.List;

/**
 * Created by jimmy on 6/20/2017.
 */
public class UserInfo {
    private String name;
    private String emailAddress;

    public UserInfo(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    private static final String QUERY_PRODUCT = "Select name from subscriptions "
            + "where product_id= '%s' "
            + "and send_mail=1 ";

    public static List<UserInfo> getUserInfo(String productId) {
        System.out.println("loadQuery set");
        return DBUtil.query(String.format(QUERY_PRODUCT, productId));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
