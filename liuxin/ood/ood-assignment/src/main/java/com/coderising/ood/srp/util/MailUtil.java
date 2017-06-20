package com.coderising.ood.srp.util;

import com.coderising.ood.srp.DO.UserInfo;

import java.util.List;


/**
 * 邮件发送类。
 * 管理邮箱连接，发送等操作。
 * @since 06.19.2017
 */
public class MailUtil {

    /**
     * SMTP连接失败异常。
     * 可用getMessage()获得异常内容。
     */
    public static class SMTPConnectionFailedException extends Throwable{
        public SMTPConnectionFailedException(String message){
            super(message);
        }
    }

    /**
     * 主SMTP服务器地址
     */
	public static final String SMTP_SERVER = "smtp.163.com";

    /**
     * 备用SMTP服务器地址
     */
	public static final String ALT_SMTP_SERVER = "smtp1.163.com";

    /**
     * 以哪个邮箱地址发送给用户
     */
	public static final String EMAIL_ADMIN = "admin@company.com";


    /**
     * 邮件发送操作。
     * 将降价促销邮件逐个发送给用户信息表中对应的用户。
     * 捕获SMTPConnectionFailedException。提示手动发送。并继续发送下一封邮件。
     * @param usersList 用户信息表。包含用户姓名，邮箱和订阅产品名称。
     */
	public static void sendEmails(List<UserInfo> usersList){
		if (usersList == null) {
			System.out.println("没有邮件发送");
			return;
		}

		for (UserInfo info : usersList){
			if (!info.getEmail().isEmpty()) {
				String emailInfo = generatePromotionEmail(info);
				try {
                    sendPromotionEmail(emailInfo);
                } catch (SMTPConnectionFailedException e){
                    System.out.println("SMTP主副服务器连接失败，请手动发送以下邮件： \n");
                    System.out.println(e.getMessage());
                }
			}
		}
	}

    /**
     * 假装在发邮件。默认使用主SMTP发送，若发送失败则使用备用SMTP发送。
     * 仍然失败，则抛出SMTPConnectFailException异常。
     * @param emailInfo 要发送的邮件内容
     * @throws SMTPConnectionFailedException 若主副SMTP服务器均连接失败，抛出异常。异常中包含完整的发送失败的邮件内容。可通过getMessage()方法获得邮件内容。
     */
	private static void sendPromotionEmail(String emailInfo) throws SMTPConnectionFailedException{
		//默认以SMTP_SERVER 发送
		//如果发送失败以ALT_SMTP_SERVER 重新发送
		//如果还失败，throw new SMTPConnectionFailedException(emailInfo).
	}

    /**
     * 根据用户信息生成促销邮件内容。
     * @param userInfo 用户信息。
     * @return 返回生成的邮件。
     */
	private static String generatePromotionEmail(UserInfo userInfo){
		StringBuilder buffer = new StringBuilder();

		buffer.append("From:").append(EMAIL_ADMIN).append("\n");
		buffer.append("To:").append(userInfo.getEmail()).append("\n");
		buffer.append("Subject:").append("您关注的产品降价了").append("\n");
		buffer.append("Content:").append("尊敬的").append(userInfo.getName());
		buffer.append(", 您关注的产品 ").append(userInfo.getProductDesc());
		buffer.append(" 降价了，欢迎购买!").append("\n");

		System.out.println(buffer.toString());

		return buffer.toString();
	}
}
