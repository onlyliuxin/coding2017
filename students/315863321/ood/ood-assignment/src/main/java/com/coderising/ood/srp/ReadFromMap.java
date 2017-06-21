package com.coderising.ood.srp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 2017/6/14.
 */
public class ReadFromMap extends Reader{
    Configuration config = new Configuration();

    List read() {
        List list = new ArrayList();
        list.add(config.getProperty(ConfigurationKeys.SMTP_SERVER));
        list.add(config.getProperty((ConfigurationKeys.ALT_SMTP_SERVER)));
        return list;
    }
}
