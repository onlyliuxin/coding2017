package com.ood.srp.util;


/**
 * 邮件发送类。
 * 管理邮箱连接，发送等操作。
 *
 * @since 06.19.2017
 */
public class MailUtil {

    /**
     * SMTP连接失败异常。
     * 可用getMessage()获得异常内容。
     */
    public static class SMTPConnectionFailedException extends Throwable {
        public SMTPConnectionFailedException(String message) {
            super(message);
        }
    }


    /**
     * 假装在发邮件。默认使用主SMTP发送，若发送失败则使用备用SMTP发送。
     * 仍然失败，则抛出SMTPConnectFailException异常。
     *
     * @param emailInfo 要发送的邮件内容
     * @throws SMTPConnectionFailedException 若主副SMTP服务器均连接失败，抛出异常。异常中包含完整的发送失败的邮件内容。可通过getMessage()方法获得邮件内容。
     */
    public static void sendPromotionEmail(String emailInfo) throws Exception {
        //默认以SMTP_SERVER 发送
        //如果发送失败以ALT_SMTP_SERVER 重新发送
        //如果还失败，throw new SMTPConnectionFailedException(emailInfo).
    }


}
