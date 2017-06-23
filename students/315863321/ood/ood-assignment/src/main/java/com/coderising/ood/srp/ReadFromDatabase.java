package com.coderising.ood.srp;

import java.util.List;

/**
 * Created by john on 2017/6/14.
 */
public class ReadFromDatabase extends Reader {
    Configuration config = new Configuration();
    Product product = null;

    public ReadFromDatabase(Product product) {
        this.product = product;
    }

    List read() {
        System.out.println("loadQuery set");
        return DBUtil.query(config.getProperty(String.format(ConfigurationKeys.SEND_MAIL_QUERY, product.getProductID())));

    }
}
