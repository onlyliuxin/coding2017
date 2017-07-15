package com.coderising.ood.answer.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 配置文件读取工具
 * @author readke
 *
 */
public class ConfigUtils {
	
	private static final Logger log = LogManager.getLogger(ConfigUtils.class);
	private static Properties prop =  null;
	
	static {
		InputStream in = ConfigUtils.class.getResourceAsStream("../config/config.properties");
		prop = new Properties();
		//System.out.println(in);
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getProperty(String key){
		return prop.getProperty(key);
	}
	
	public static void main(String[] args) {
		log.info(ConfigUtils.getProperty("smtp.server"));
	}
}
