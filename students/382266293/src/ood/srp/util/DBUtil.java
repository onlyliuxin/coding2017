package ood.srp.util;

import ood.srp.bean.Product;

public class DBUtil {

    public static void setLoadQuery(Product p) {

        String sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + p.getProductID() + "' "
                + "and send_mail=1 ";

        System.out.println("loadQuery set");
    }

}
