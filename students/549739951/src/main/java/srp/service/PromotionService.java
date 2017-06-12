package srp.service;

import srp.config.Constant;
import srp.model.Email;

/**
 * @version V1.0
 * @Title： PromotionService
 * @Package： srp.service
 * @Description： Promotion服务类 省去接口部分
 * @author： 南来
 * @date： 2017-06-12 10:24
 */
public class PromotionService {

    private MailService mailService = new MailService();

    /**
     * 发送促销邮件
     *
     * @param name
     * @param toAddress 邮箱地址
     * @param desc
     */
    public void promotionMail(String name, String toAddress, String desc) {
        mailService.send(build(name, toAddress, desc));
    }

    /**
     * 构建Email对象
     *
     * @param name      姓名
     * @param toAddress 邮箱地址
     * @param desc      商品描述
     * @return Email对象
     */
    private Email build(String name, String toAddress, String desc) {
        Email email = new Email();
        email.setFrom(Constant.EMAIL_ADMIN);
        email.setTo(toAddress);
        email.setSubject(Constant.SUBJECT);
        email.setContent(String.format(Constant.MESSAGE, name, desc));
        return email;
    }

}
