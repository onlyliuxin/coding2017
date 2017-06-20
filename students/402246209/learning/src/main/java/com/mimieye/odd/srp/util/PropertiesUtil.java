package com.mimieye.odd.srp.util;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Pierreluo on 2017/6/17.
 */
public class PropertiesUtil {

    public static Properties getInstance(String fileName) throws Exception {
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
        Properties properties = new Properties();
        properties.load(in);
        return properties;
    }
}
