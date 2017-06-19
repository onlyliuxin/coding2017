package com.coderising.ood.srp;

import java.util.List;

/**
 * Created by 360682644 on 2017/6/13.
 */
public class ReceiverService {

    private final static ReceiverService service = new ReceiverService();
    public static ReceiverService getInstance(){return service;}

    public List<MailReceiver> loadMailingList(Product product){
        return DBUtil.query(getLoadQuery(), product);
    }

    public String getLoadQuery(){
        String query = "Select name from subscriptions "
                + "where product_id= ? "
                + "and send_mail=1 ";
        System.out.println("loadQuery set");
        return query;
    }
}
