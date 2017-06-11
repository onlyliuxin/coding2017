package com.coderising.ood.srp;

/**
 * Created by zhenli on 17/6/11.
 */
public class Query {
    private String sendMailQuery = null;

    public String getSendMailQuery() {
        return sendMailQuery;
    }

    protected void setLoadQuery(Product product) throws Exception {

        sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + product.getProductID() +"' "
                + "and send_mail=1 ";


        System.out.println("loadQuery set");
    }

}
