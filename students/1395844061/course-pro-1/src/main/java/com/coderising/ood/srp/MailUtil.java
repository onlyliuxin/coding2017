package com.coderising.ood.srp;

/**
 * MailUtil
 *
 * @author Chenpz
 * @package com.coderising.ood.srp
 * @date 2017/6/12/23:28
 */
public final class MailUtil {


    private MailUtil(){
        throw new RuntimeException("illegal called!");
    }

    public static void sendEmail(String toAddress, String fromAddress, String subject, String message, String smtpHost,
                                 boolean debug) {
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(fromAddress).append("\n");
        buffer.append("To:").append(toAddress).append("\n");
        buffer.append("Subject:").append(subject).append("\n");
        buffer.append("Content:").append(message).append("\n");
        System.out.println(buffer.toString());
    }
}
