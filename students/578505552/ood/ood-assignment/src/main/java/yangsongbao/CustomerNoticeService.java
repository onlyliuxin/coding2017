package yangsongbao;

import yangsongbao.model.ProductInfo;
import yangsongbao.model.UserInfo;
import yangsongbao.utils.MailUtil;

import java.util.List;

/**
 *
 * Created by songbao.yang on 2017/6/17.
 */
public class CustomerNoticeService {

    public void noticeByEmail(List<UserInfo> userInfos, NoticeConditionEnum noticeType, ProductInfo productInfo){

        String mailSubject = MailContentManager.getMailSubject(noticeType);
        String altSmtpServer = Configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER);
        String fromAddress = Configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN);
        String smtpServer = Configuration.getProperty(ConfigurationKeys.SMTP_SERVER);

        for (UserInfo userInfo : userInfos){
            System.out.println("开始给用户【" + userInfo.getName() + "】发送邮件");
            String toAddress = userInfo.getEmail();
            String mailBody = MailContentManager.generateMailBody(userInfo, noticeType, productInfo);

            boolean sendResult = MailUtil.sendEmail(toAddress, fromAddress, mailSubject, mailBody, smtpServer);
            if (sendResult){
                System.out.println("通过SMTP服务器给邮箱【" + toAddress + "】发送邮件成功");
            } else {
                System.out.println("通过SMTP服务器给邮箱【" + toAddress + "】发送邮件成功，尝试通过备用 SMTP服务器发送邮件");
                sendResult = MailUtil.sendEmail(toAddress, fromAddress, mailSubject, mailBody, altSmtpServer);
                if (!sendResult){
                    System.out.println("通过备用SMTP服务器给邮箱【】" + toAddress + "发送邮件失败");
                }
            }
            System.out.println("给用户【" + userInfo.getName() + "】发送邮件完毕");
            System.out.println();
        }

    }

}
