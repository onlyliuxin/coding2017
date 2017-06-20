package homework.jyz.coding2017;

import java.util.List;

/**
 * 发送邮件的工作类
 * Created by jyz on 2017/6/13.
 */
public class MailSend {

    private EmailHost host;

    public MailSend(EmailHost host){
        this.host = host;
    }

    public MailSend() {
    }

    /**
     * 默认主机发送
     * @param emails
     */
    public  void  send(List<Email> emails){
        emails.forEach(email -> host.send(email));
    }
    public  void  send(Email email){
        host.send(email);
    }
    /**
     * 指定主机发送
     * @param host
     * @param emails
     */
    public void send(EmailHost host,List<Email> emails){
        for (Email email : emails) {
            host.send(email);
        }
    }

}
