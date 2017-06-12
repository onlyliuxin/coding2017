package first.ood.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gongxun on 2017/6/12.
 */
public class EmailParser {

    public List<PromotionMail> parseEmailList(String filepath, String loadQuery, Object[] params) {
        PromotionMail email = packageInfoFromConfig();
        packageInfoFromFile(email, filepath);
        return packageInfoFromDB(loadQuery, email, params);
    }

    private String parseMessage(HashMap<String, String> map, PromotionMail promotionMail) {
        String name = map.get(ConfigurationKeys.NAME_KEY);
        String message = "尊敬的 " + name + ", 您关注的产品 " + promotionMail.getProductDesc() + " 降价了，欢迎购买!";
        return message;
    }

    private String parseToAddress(HashMap<String, String> map) {
        return map.get(ConfigurationKeys.EMAIL_KEY);
    }


    private List<PromotionMail> packageInfoFromDB(String loadQuery, PromotionMail email, Object[] params) {
        List<HashMap<String, String>> individualInfo = getIndividualInfo(loadQuery, params);
        List<PromotionMail> mailList = new ArrayList<PromotionMail>();
        for (HashMap<String, String> map : individualInfo) {
            PromotionMail completeMail = new PromotionMail();
            BeanUtils.copyProperties(completeMail, email);
            completeMail.setToAddress(parseToAddress(map));
            completeMail.setMessage(parseMessage(map, completeMail));
            completeMail.setSubject("您关注的产品降价了");
            mailList.add(completeMail);
        }
        return mailList;
    }

    private PromotionMail packageInfoFromFile(PromotionMail email, String filepath) {
        FileParser fileParser = new FileParser(filepath);
        email.setProductDesc(fileParser.parseProductDesc());
        email.setProductID(fileParser.parseProductID());
        return email;
    }

    private PromotionMail packageInfoFromConfig() {
        PromotionMail email = new PromotionMail();

        Configuration configuration = new Configuration();
        email.setSMTPHost(configuration.getProperty(ConfigurationKeys.SMTP_SERVER));
        email.setFromAddress(configuration.getProperty(ConfigurationKeys.EMAIL_ADMIN));
        email.setAltSMTPHost(configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
        return email;
    }

    private List<HashMap<String, String>> getIndividualInfo(String loadQuery, Object[] params) {
        return DBUtil.query(loadQuery, params);
    }
}
