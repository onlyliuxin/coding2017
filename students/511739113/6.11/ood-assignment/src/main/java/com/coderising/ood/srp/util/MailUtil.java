package com.coderising.ood.srp.util;

import com.coderising.ood.srp.bean.Message;

/**
 * 消息推送工具类
 * <p>标题: </p>
 * <p>描述: </p>
 * @autho zhangxu
 * @time 2017年6月13日 上午2:03:46
*/
public class MailUtil {

	public static void sendEmail(Message message,boolean debug) {
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(message.getFromAddress()).append("\n");
		buffer.append("To:").append(message.getToAddress()).append("\n");
		buffer.append("Subject:").append(message.getSubject()).append("\n");
		buffer.append("Content:").append(message.getMessage()).append("\n");
		System.out.println(buffer.toString());
	}

	
}
