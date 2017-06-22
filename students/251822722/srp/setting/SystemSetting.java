package com.coderising.ood.srp.setting;

import com.coderising.ood.srp.setting.config.Configuration;
import com.coderising.ood.srp.setting.config.ConfigurationKeys;

/**
 * com.coderising.ood.srp.setting
 * Created by Eric Wang on 6/19/17.
 */
public class SystemSetting {


    private static Configuration config = new Configuration();



    public static Configuration getConfig() {
        return config;
    }

    public static String getAdmin() {

        return config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
    }

}
