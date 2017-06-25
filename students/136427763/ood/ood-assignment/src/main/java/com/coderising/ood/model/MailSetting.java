package com.coderising.ood.model;

import com.coderising.ood.config.Configuration;
import com.coderising.ood.config.ConfigurationKeys;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年6月17日 下午9:50:47 类说明
 */
public class MailSetting {
	private String mSmtpHost;

	private String mAltmSmtpHost;

	private Configuration mConfig;

	private String mFromAddress;
	
	public String getmFromAddress() {
		return mFromAddress;
	}

	private static MailSetting mailSetting=null;
	
	private static final Object mObject=new Object();

	public static MailSetting getInstannce(){
		synchronized (mObject) {
			if(null==mailSetting){
				mailSetting =new MailSetting();
				return mailSetting;
			}
			return mailSetting;
		}
	}
	
	public MailSetting() {
		mConfig=new Configuration();
		init();
	}

	private void init() {
		setmAltmSmtpHost();
		setmFromAddress();
		setmSmtpHost();
	}

	public String getmSmtpHost() {
		return mSmtpHost;
	}

	public String getmAltmSmtpHost() {
		return mAltmSmtpHost;
	}

	protected void setmAltmSmtpHost() {
		mAltmSmtpHost = mConfig.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
	}

	protected void setmFromAddress() {
		mFromAddress = mConfig.getProperty(ConfigurationKeys.EMAIL_ADMIN);
	}

	protected void setmSmtpHost() {
		mSmtpHost = mConfig.getProperty(ConfigurationKeys.SMTP_SERVER);
	}

}
