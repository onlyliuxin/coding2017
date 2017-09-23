package homework.jyz.coding2017;

/**
 * 邮件服务器主机类
 * Created by jyz on 2017/6/13.
 */
public class EmailHost {
    private Configuration config;
    private String host;
    private String altHost;
    private String hostAdmin;


    /**
     * 构建主机
     * @param config 主机配置
     */
    public EmailHost(Configuration config) {
        this.config = config;
        host = this.config.getProperty(Configuration.SMTP_SERVER);
        altHost = this.config.getProperty(Configuration.ALT_SMTP_SERVER);
        hostAdmin = this.config.getProperty(Configuration.EMAIL_ADMIN);
    }

    public boolean send(Email email){

        if(email != null){
            StringBuilder buffer = new StringBuilder();
            buffer.append("From:").append(email.getFromAddress()).append("\n");
            buffer.append("To:").append(email.getToAddress()).append("\n");
            buffer.append("Subject:").append(email.getSubject()).append("\n");
            buffer.append("Content:").append(email.getMessage()).append("\n");
            if(send(host,buffer.toString())){
                return true;
            }
            System.out.println("启用备用主机发送..");
            if(send(altHost,buffer.toString())){
                return true;
            }
            System.out.println("发送失败");
            return false;
        }
        System.out.println("邮件为空，发送失败");
        return false;
    }

    public boolean send(String host,String message){
        try {
            System.out.println("使用主机"+host+"发送邮件");
            System.out.println(message);
            return true;
        } catch (Exception e) {
            System.out.println("使用主机"+host+"发送邮件失败");
            e.printStackTrace();
            return false;
        }
    }
}
