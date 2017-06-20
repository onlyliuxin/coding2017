package srp.refactor.services;

import org.apache.commons.lang3.StringUtils;
import srp.refactor.mail.MailClient;
import srp.refactor.util.Constants;

import java.util.HashMap;
import java.util.List;

/**
 * 邮件收发服务
 *
 * Created by Tee on 2017/6/16.
 */
public class MailService {


    /**
     * 批量发送
     */
    public void batchSend(boolean debug, MailClient mailClient, List<HashMap> mailList){
        if(mailList.isEmpty()){
            System.out.println("没有邮件要发送");
            return;
        }
        int size = mailList.size();
        System.out.println("开始发送邮件, 总邮件数=" + size);
        int i = 0;
        for(HashMap mail : mailList){
            i++;
            String toAddress = (String)mail.get(Constants.EmailInfo.TO_ADDRESS_KEY.getKey());
            if(StringUtils.isBlank(toAddress)){
                System.out.println("收件人地址为空，此邮件发送中止");
                continue;
            }

            String subject = (String)mail.get(Constants.EmailInfo.SUBJECT_KEY.getKey());
            String message = (String)mail.get(Constants.EmailInfo.MESSAGE_KEY.getKey());

            System.out.println("\n正在发送第[" + i + "]封邮件");
            System.out.println("==========================================================");
            try{
                sendEmail(toAddress, mailClient.getFromAddress(), subject, message, mailClient.getSmtpHost(), debug);
            }catch(Exception e){
                sendEmail(toAddress, mailClient.getFromAddress(), subject, message, mailClient.getAltSmtpHost(), debug);
            }
            System.out.println("==========================================================");
            System.out.println("第[" + i + "]封邮件发送完成");
        }
    }

    /**
     * 发送邮件客户端的功能和责任，所以移入邮件客户端，但是只能被基础客户端调用
     * 子类只能通过batchSend来发送邮件
     */
    public void sendEmail(String toAddress, String fromAddress, String subject,
                           String message, String smtpHost, boolean debug) {
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(fromAddress).append("\n");
        buffer.append("To:").append(toAddress).append("\n");
        buffer.append("Subject:").append(subject).append("\n");
        buffer.append("Content:").append(message).append("\n");
        System.out.print(buffer.toString());
    }
}
