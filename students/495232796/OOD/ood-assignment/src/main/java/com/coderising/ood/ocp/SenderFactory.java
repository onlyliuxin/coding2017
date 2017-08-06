package com.coderising.ood.ocp;

public class SenderFactory {
	public static Sender createSender(int type) {
		if(type == LogType.EMAIL_LOG){
			return new MailUtil();
		} else if(type == LogType.SMS_LOG){
			return new SMSUtil();
		} else if(type == LogType.PRINT_LOG){
			return new ComSender();
		}
		
		return new ComSender();
	}
}
