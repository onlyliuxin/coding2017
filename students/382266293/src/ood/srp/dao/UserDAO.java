package ood.srp.dao;

import ood.srp.bean.Product;
import ood.srp.bean.Subscriber;
import ood.srp.util.DBUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by onlyLYJ on 2017/6/12.
 */
public class UserDAO {

    public List<Subscriber> list(Product p) throws Exception {

        DBUtil.setLoadQuery(p);

        List<Subscriber> subscribers = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Subscriber s = new Subscriber();
            s.setName("user" + i);
            s.setMailAddress(s.getName() + "@amazon.com");
            subscribers.add(s);

        }
        return subscribers;

    }

}
