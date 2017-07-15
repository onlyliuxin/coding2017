package ood.srp1;

import ood.srp1.entity.Email;
import ood.srp1.entity.Product;
import ood.srp1.server.MainSmtpFactory;
import ood.srp1.server.SmtpServer;
import ood.srp1.server.TempSmtpFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 邮件发送器
 * Created by lx on 2017/6/17.
 */
public class MailSender {

    protected SmtpServer mainServer = new MainSmtpFactory().createSmtp();
    protected SmtpServer tempServer = new TempSmtpFactory().createSmtp();
    public static final String NAME_KEY = "NAME";

    /**
     * 批量发送邮件
     * @param p 产品信息
     * @param sendUserList 用户信息
     * @param d
     * @throws java.io.IOException
     */
    public void batchSendEMail(Product p, List<Map> sendUserList, boolean d) throws IOException {
        System.out.println("--------开始发送邮件-------");
        if (null == sendUserList || sendUserList.size() == 0) {
            System.out.println("没有邮件发送");
            return;
        }

        for (Map userInfo : sendUserList) {
            Email email = new Email();
            String toAddr = userInfo.get("EMAIL").toString();
            email.setToAddress(toAddr);
            email.setFromAddress(mainServer.address);
            email.setSubject("您关注的产品降价了");
            email.setMessage("尊敬的 " + userInfo.get(NAME_KEY).toString() + ", 您关注的产品 " + p.getProductDesc() + " 降价了，欢迎购买!");
            try {
                sendEmail(email, d);
            } catch (Exception e) {
                try {
                    email.setFromAddress(tempServer.address);
                    sendEmail(email, d);
                } catch (Exception e2) {
                    System.out.println("通过备用 SMTP服务器发送邮件失败: " + e2.getMessage());
                }
            }
        }

    }

    /**
     * 发送邮件
     * @param n
     * @param debug
     */
    public static void sendEmail(Email n, boolean debug) {
        //假装发了一封邮件
        StringBuilder buffer = new StringBuilder();
        buffer.append("From:").append(n.getFromAddress()).append("\n");
        buffer.append("To:").append(n.getToAddress()).append("\n");
        buffer.append("Subject:").append(n.getSubject()).append("\n");
        buffer.append("Content:").append(n.getMessage()).append("\n");
        System.out.println(buffer.toString());

    }
}
