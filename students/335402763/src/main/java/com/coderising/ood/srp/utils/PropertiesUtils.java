package com.coderising.ood.srp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {
	
	private static Map<String,Object> props=new HashMap<String,Object>();
	private static Properties properties=new Properties();
	
	public static final String SMTP_SERVER = "SMTP_SERVER";
	public static final String ALT_SMTP_SERVER = "ALT_SMTP_SERVER";
	public static final String EMAIL_ADMIN = "EMAIL_ADMIN";
	static{
		try {
			InputStream inStream=PropertiesUtils.class.getClassLoader().getResourceAsStream("configurationKeys.properties");
			properties.load(inStream);

			props.put(SMTP_SERVER, properties.get(SMTP_SERVER));
			props.put(ALT_SMTP_SERVER, properties.get(ALT_SMTP_SERVER));
			props.put(EMAIL_ADMIN, properties.get(EMAIL_ADMIN));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Object get(String key){
		return props.get(key);
	}
	
	public static void set(String key,Object obj){
		props.put(key, obj);
	}
	
}
