package srp.service;

import srp.model.Email;
import srp.util.MailUtil;

import static srp.config.Config.altSmtpHost;
import static srp.config.Config.smtpHost;

/**
 * @version V1.0
 * @Title： MailService
 * @Package： srp.service
 * @Description： e-mail服务类 省去接口部分
 * @author： 南来
 * @date： 2017-06-12 10:26
 */
public class MailService {

    /**
     * 负责发送邮件对象
     *
     * @param email email对象
     */
    public void send(Email email) {
        if (MailUtil.send(email, smtpHost)) {
            System.out.println(String.format("ServerHost： %s , 邮件内容： %s ", smtpHost, email));
        } else {
            System.out.println("主邮件服务器发送失败，尝试使用备用服务器发送……");
            if (MailUtil.send(email, altSmtpHost)) {
                System.out.println(String.format("ServerHost： %s , 邮件内容： %s ", altSmtpHost, email));
            } else {
                System.err.println("使用备用服务器发送失败……(╯°Д°)╯︵┻━┻");
            }
        }
    }
}
