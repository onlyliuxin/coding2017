package com.coderising.ood.srp.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.coderising.ood.srp.entity.Msg;

public class PropertiesUtil {
	public static final Properties pro;
	public static final Msg BASEMSG = new Msg();
	
	public static final String SMTP_SERVER = "smtp.server";
	public static final String ALT_SMTP_SERVER = "alt.smtp.server";
	public static final String EMAIL_ADMIN = "email.admin";
	
	
	static{
		pro = new Properties();
		FileInputStream in = null ;
		try {
			in= new FileInputStream("C:\\Users\\Administrator\\git\\coding2017\\students\\641013587\\ood\\ood-assignment\\src\\main\\java\\com\\coderising\\ood\\srp\\values.properties");
			pro.load(in);
			BASEMSG.setAltSmtpHost(pro.getProperty(ALT_SMTP_SERVER));
			BASEMSG.setSmtpHost(SMTP_SERVER);
			BASEMSG.setFromAddress(EMAIL_ADMIN);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
