package ood.srp1;

import ood.srp1.entity.Product;
import ood.srp1.server.UserServer;
import ood.srp1.server.ProductServer;

import java.util.List;
import java.util.Map;

/**
 *  promotion 提升
 */
public class PromotionMail {
    // 用户信息
    private static UserServer ms = new UserServer();
    // 邮件发送器
    private static MailSender mSend = new MailSender();

    public static void main(String[] args) throws Exception {
        // 获取产品信息
        List<Product> pList = ProductServer.getUserProduct();

        for (Product p : pList) {
            System.out.println("产品ID： " + p.getProductId() + "\n" + "产品描述：" + p.getProductDesc());
            // 获取接收产品用户列表
            List<Map> sendUserList = ms.querySendUser(p.getProductId());
            // 发送邮件
            mSend.batchSendEMail(p, sendUserList, false);
        }

    }
}
