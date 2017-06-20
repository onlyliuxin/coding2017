package com.mimieye.odd.srp.config;
import com.mimieye.odd.srp.util.PropertiesUtil;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configuration {
	private static final String CONFIG_FILENAME = "config.properties";
	static Properties configurations = null;
	static{
        try {
            configurations = PropertiesUtil.getInstance(CONFIG_FILENAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	/**
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return configurations.getProperty(key);
	}

}
