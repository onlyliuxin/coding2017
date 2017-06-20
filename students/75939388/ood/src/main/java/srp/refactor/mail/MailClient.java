package srp.refactor.mail;

import org.apache.commons.lang3.StringUtils;
import srp.refactor.services.MailService;
import srp.refactor.util.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 具有初始化设置、可以批量发送邮件的邮件客户端
 *
 * Created by Tee on 2017/6/15.
 */
public abstract class MailClient {
    protected String smtpHost = null;
    protected String altSmtpHost = null;
    protected String fromAddress = null;
    protected String toAddress = null;
    protected String subject = null;
    protected String message = null;

    protected List<HashMap> mailList;

    private MailService mailService = new MailService();
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
    protected void addToMailList(){
        HashMap<String, Object> mailToSend = new HashMap<>();
        mailToSend.put(Constants.EmailInfo.TO_ADDRESS_KEY.getKey(), this.toAddress);
        mailToSend.put(Constants.EmailInfo.SUBJECT_KEY.getKey(), this.subject);
        mailToSend.put(Constants.EmailInfo.MESSAGE_KEY.getKey(), this.message);
        this.mailList.add(mailToSend);
    }

    private boolean isInit(){
        return StringUtils.isNotBlank(this.smtpHost) &&
                StringUtils.isNotBlank(this.altSmtpHost) &&
                StringUtils.isNotBlank(this.fromAddress);
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getAltSmtpHost() {
        return altSmtpHost;
    }

    public void setAltSmtpHost(String altSmtpHost) {
        this.altSmtpHost = altSmtpHost;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * 调用邮件邮件服务器来收发邮件
     * @param debug
     */
    public void batchSend(boolean debug){
        if(!isInit()){
            throw new RuntimeException("邮件客户端还未做配置");
        }

        mailService.batchSend(debug, this, this.mailList);
    }
}
