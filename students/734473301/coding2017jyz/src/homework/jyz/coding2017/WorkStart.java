package homework.jyz.coding2017;

import java.io.File;
import java.io.IOException;

/**
 *  主程序类
 * Created by jyz on 2017/6/13.
 */
public class WorkStart {
    public static void main(String args[]) throws IOException {
        // 发送邮件程序入口
        Configuration confg = new Configuration();// 加载配置
        MailSend ms = new MailSend(new EmailHost(confg));// 创建邮件工作类
        MailConfig mc  = new MailConfig(new MailDao());// 组织待发送邮件
        mc.readFile(new File("product_promotion.txt"));// 获取产品信息
        ms.send(mc.getEmails());// 批量发送

        Email email = new Email("from@qq.com","to@qq.com","coding2017","hello,world!");
        ms.send(email); // 单个发送

    }



}
