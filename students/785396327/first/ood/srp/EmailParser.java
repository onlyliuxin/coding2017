package first.ood.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gongxun on 2017/6/12.
 */
public class EmailParser {

    public List<PromotionMail> parseEmailList(String filepath, String loadQuery) {
        List<PromotionMail> mailList = new ArrayList<PromotionMail>();
        Email email = parseCommonInfo(filepath);
        List<HashMap<String, String>> individualInfo = getIndividualInfo(loadQuery);
        for (HashMap<String, String> map : individualInfo) {
            PromotionMail promotionMail = new PromotionMail(email);
            promotionMail.setToAddress(parseToAddress(map));
            promotionMail.setMessage(parseMessage(map, promotionMail));
            promotionMail.setSubject("您关注的产品降价了");
            mailList.add(promotionMail);
        }
        return mailList;
    }

    private String parseMessage(HashMap<String, String> map, PromotionMail promotionMail) {
        String name = map.get(ConfigurationKeys.NAME_KEY);
        String message = "尊敬的 " + name + ", 您关注的产品 " + promotionMail.getProductDesc() + " 降价了，欢迎购买!";
        return message;
    }

    private String parseToAddress(HashMap<String, String> map) {
        return map.get(ConfigurationKeys.EMAIL_KEY);
    }

    private PromotionMail parseCommonInfo(String filepath) {
        Email email = new Email();

        FileParser fileParser = new FileParser(filepath);
//        email.setProductID(fileParser.parseProductID());
//        email.setProductDesc(fileParser.parseProductDesc());

        Configuration configuration = new Configuration();
//        promotionMail.setSMTPHost(configuration.getProperty(ConfigurationKeys.SMTP_SERVER));
//        promotionMail.setFromAddress(configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN));
//        promotionMail.setAltSMTPHost(configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
//        return promotionMail;
        return null;
    }

    private List<HashMap<String, String>> getIndividualInfo(String loadQuery) {
        return DBUtil.query(loadQuery);
    }
}
