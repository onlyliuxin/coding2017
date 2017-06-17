package yangsongbao;

import yangsongbao.model.ProductInfo;
import yangsongbao.model.UserInfo;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by songbao.yang on 2017/6/17.
 */
public class MailContentManager {

    private static Map<NoticeConditionEnum, String> mailSubjectConf = new HashMap<NoticeConditionEnum, String>();
    private static Map<NoticeConditionEnum, String> mailBodyTemplateConf = new HashMap<NoticeConditionEnum, String>();

    static {
        mailSubjectConf.put(NoticeConditionEnum.PROMOTION, "您关注的产品降价了");
        mailBodyTemplateConf.put(NoticeConditionEnum.PROMOTION, "尊敬的%s, 您关注的产品%s降价了，欢迎购买!");
    }

    public static String getMailSubject(NoticeConditionEnum noticeCondition){
        return mailSubjectConf.get(noticeCondition);
    }

    private static String getMailBodyTemplate(NoticeConditionEnum noticeCondition){
        return mailBodyTemplateConf.get(noticeCondition);
    }

    public static String generateMailBody(UserInfo userInfo, NoticeConditionEnum noticeType, ProductInfo productInfo){

        String userName = userInfo.getName();
        String desc = productInfo.getDesc();

        String mailBodyTemplate = getMailBodyTemplate(noticeType);
        return String.format(mailBodyTemplate, userName, desc);
    }

}
