package cn.net.pikachu.base;

import cn.net.pikachu.domain.MailSetting;

import java.util.*;

/**
 * 
 */
public abstract class Mail {

    protected MailSetting mailSetting = new MailSetting();
    protected MailContentTemplate mailContentTemplate;
    protected Map<String,Object> params = new HashMap<>();
    /**
     * Default constructor
     */
    public Mail() {
    }

    /**
     * 发送邮件
     */
    public void send() {
        //假装发了一封邮件
        try {
            this._send(mailSetting.getSmtpHost());
        }catch (Exception exception){
            System.out.println("通过"+mailSetting.getSmtpHost()+"发送失败");
            System.out.println("使用备用stmp服务器发送");
            try {
                this._send(mailSetting.getAltSmtpHost());
            }catch (Exception e){
                System.out.println("使用备用服务器发送失败:"+e.getMessage());
            }
        }
    }
    private void _send(String stmpHost){
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(mailSetting.getFromAddress()).append("\n");
        buffer.append("To:").append(mailSetting.getToAddress()).append("\n");
        buffer.append("Subject:").append(mailSetting.getSmtpHost()).append("\n");
        buffer.append("Content:").append(mailContentTemplate.render()).append("\n");
        System.out.println(buffer.toString());
    }

}