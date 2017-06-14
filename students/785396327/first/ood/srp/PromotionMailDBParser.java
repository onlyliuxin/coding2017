package first.ood.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gongxun on 2017/6/14.
 */
public class PromotionMailDBParser extends DBParser<PromotionMail> {

    protected PromotionMailDBParser(String sql) {
        super(sql);
    }

    @Override
    List<PromotionMail> convertData(Email email, List<HashMap<String, String>> data) {
        List<PromotionMail> mailList = new ArrayList<PromotionMail>();
        for (HashMap<String, String> map : data) {
            PromotionMail completeMail = new PromotionMail();
            BeanUtils.copyProperties(completeMail, email);
            completeMail.setToAddress(parseToAddress(map));
            completeMail.setMessage(parseMessage(map, completeMail));
            completeMail.setSubject("您关注的产品降价了");
            mailList.add(completeMail);
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
}
