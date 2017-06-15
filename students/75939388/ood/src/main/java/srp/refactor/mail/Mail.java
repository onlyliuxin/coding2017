package srp.refactor.mail;

import org.apache.commons.lang3.StringUtils;
import srp.refactor.util.MailUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tee on 2017/6/15.
 */
public abstract class Mail {
    protected String smtpHost = null;
    protected String altSmtpHost = null;
    protected String fromAddress = null;
    protected String toAddress = null;
    protected String subject = null;
    protected String message = null;

    private static final String TO_ADDRESS_KEY = "toAddress";
    private static final String SUBJECT_KEY = "subject";
    private static final String MESSAGE_KEY = "message";

    protected List<HashMap> mailList;

    /**
     * 初始化邮件设置
     *
     * @param smtpHost smtp主机
     * @param altSmtpHost 备用smtp主机
     * @param fromAddress 发件人地址
     */
    protected void initMailSettings(String smtpHost, String altSmtpHost, String fromAddress){
        this.smtpHost = smtpHost;
        this.altSmtpHost = altSmtpHost;
        this.fromAddress = fromAddress;
        this.mailList = new ArrayList<>();
    }

    public abstract void createMail(String toAddress, String subject, String message);

    /**
     * 撰写邮件
     */
    protected void write(){
        HashMap<String, Object> mailToSend = new HashMap<>();
        mailToSend.put(TO_ADDRESS_KEY, this.toAddress);
        mailToSend.put(SUBJECT_KEY, this.subject);
        mailToSend.put(MESSAGE_KEY, this.message);
        this.mailList.add(mailToSend);
    }

    private boolean isInit(){
        return StringUtils.isNotBlank(this.smtpHost) &&
                StringUtils.isNotBlank(this.altSmtpHost) &&
                StringUtils.isNotBlank(this.fromAddress);
    }

    /**
     * 供子类实现的send方法
     */
    public abstract void send();

    /**
     * 批量发送
     */
    public void batchSend(boolean debug){
        if(!isInit()){
            throw new RuntimeException("邮件客户端还未做配置");
        }

        if(mailList.isEmpty()){
            System.out.println("没有邮件要发送");
            return;
        }
        int size = mailList.size();
        System.out.println("开始发送邮件, 总邮件数=" + size);
        int i = 0;
        for(HashMap mail : mailList){
            i++;
            String toAddress = (String)mail.get(TO_ADDRESS_KEY);
            if(StringUtils.isBlank(toAddress)){
                System.out.println("收件人地址为空，此邮件发送中止");
                continue;
            }

            String subject = (String)mail.get(SUBJECT_KEY);
            String message = (String)mail.get(MESSAGE_KEY);

            System.out.println("\n正在发送第[" + i + "]封邮件");
            try{
                MailUtil.sendEmail(toAddress, this.fromAddress, subject, message, this.smtpHost, debug);
            }catch(Exception e){
                MailUtil.sendEmail(toAddress, this.fromAddress, subject, message, this.altSmtpHost, debug);
            }
            System.out.println("第[" + i + "]封邮件发送完成");
        }
    }
}
