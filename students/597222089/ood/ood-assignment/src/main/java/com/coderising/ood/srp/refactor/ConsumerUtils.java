package com.coderising.ood.srp.refactor;

import com.coderising.ood.srp.DBUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by walker on 2017/6/20.
 */

public class ConsumerUtils {
    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";

    public static List<Consumer> getConsumers() {
        List<Consumer> consumers = new ArrayList<>();

        String sql = "";
        List query = DBUtil.query(sql);

        Iterator iter = query.iterator();
        while (iter.hasNext()) {

            HashMap<String, String> info = (HashMap<String, String>) iter.next();

            String name = info.get(NAME_KEY);
            String email = info.get(EMAIL_KEY);

            Consumer consumer = new Consumer(name, email);

            consumers.add(consumer);
        }

        return consumers;
    }
}
