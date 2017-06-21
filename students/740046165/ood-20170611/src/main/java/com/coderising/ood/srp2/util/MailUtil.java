package com.coderising.ood.srp2.util;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.coderising.ood.srp.Configuration;
import com.coderising.ood.srp.ConfigurationKeys;
import com.coderising.ood.srp2.model.FollowUser;
import com.coderising.ood.srp2.model.MimeMessage;
import com.coderising.ood.srp2.model.Product;

/**
 * 邮件发送服务
 * @author mazan
 *
 */
public class MailUtil {
	
	/**
	 * 批量发送邮件
	 * @param map
	 */
	public static void SendMail(Map<FollowUser, List<Product>> map) {
		for (Map.Entry<FollowUser, List<Product>> entry : map.entrySet()) {
			SendMail(entry.getKey(), entry.getValue());
		}
	}
	/**
	 * 发送邮件通知
	 */
	public static void SendMail(FollowUser user, List<Product> list) {
		
		// 1. 创建参数配置, 用于连接邮件服务器的参数配置
		Configuration config = new Configuration();
		//发送邮箱
		String sendMail = config.getProperty(ConfigurationKeys.EMAIL_ADMIN);
		
		//封装邮件内容
		String content = createContextByPromotionList(user, list);
		//创建一封邮件
		MimeMessage mineMessage = createMimeMessage(sendMail, user.getUserEmail(), content);
        //发送邮件
		sendMail(mineMessage);
		
	}
	/**
	 * 邮件发送--使用另外一个线程异步发送,或者MQ
	 * @param mineMessage
	 */
	private static void sendMail(MimeMessage mineMessage) {
		//校验
		try {
			checkMineMessage(mineMessage);
		} catch (Exception e) {
			//检验失败不发送
			return;
		}
		
		//假装发了一封邮件
		StringBuilder buffer = new StringBuilder();
		buffer.append("From:").append(mineMessage.getFromAddress()).append("\n");
		buffer.append("To:").append(mineMessage.getToAddress()).append("\n");
		buffer.append("Subject:").append(mineMessage.getSubject()).append("\n");
		buffer.append("Content:").append(mineMessage.getContent()).append("\n");
		
		
		try {
			System.out.println(buffer.toString());
		} catch (Exception e) {
			//备用服务器发送
			//异常：邮箱地址错误啦之类的
			e.printStackTrace();
		}
	}
	
	/**
	 * 邮件检验
	 * @param mineMessage
	 */
	private static void checkMineMessage(MimeMessage mineMessage) {
		if (mineMessage.getToAddress().length() < 0) {
			throw new RuntimeException("接收邮件地址错误");
		}
		//others...
	}
	/**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(String sendMail, String receiveMail, String context) {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage();
        // 2. From: 发件人
        message.setFromAddress(sendMail);
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setToAddress(receiveMail);
        // 4. Subject: 邮件主题
        message.setSubject("您关注的产品降价了");
        // 5. Content: 邮件正文（可以使用html标签）
        message.setContent(context);
        // 6. 设置发件时间
        message.setSendDate(new Date());


        return message;
    }
	
    /**
     * 封装邮件通知信息
     * @param user
     * @param list
     * @return
     */
    public static String createContextByPromotionList(FollowUser user, List<Product> list) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("尊敬的" + user.getUserName())
    		.append(",")
    		.append("您关注的产品")
    		.append(ListUtil.ListToString(list))
    		.append(" 降价了, 欢迎购买!");
    	return sb.toString();
    }
    
}
