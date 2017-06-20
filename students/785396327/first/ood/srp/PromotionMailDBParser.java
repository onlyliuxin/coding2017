package first.ood.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by william on 2017/6/14.
 */
public class PromotionMailDBParser extends DBParser<PromotionMail> {

    protected PromotionMailDBParser(String sql, Object[] params) {
        super(sql, params);
    }

    /**
     * 由于sql参数需要运行时提供所以重写parseInfoFromDB方法
     * @param email
     * @return
     */
    @Override
    protected List<PromotionMail> parseInfoFromDB(PromotionMail email) {
        List<HashMap<String, String>> data = DBUtil.query(super.sql, new Object[]{email.getproductID()});
        return convertData(email, data);
    }

    @Override
    List<PromotionMail> convertData(PromotionMail email, List<HashMap<String, String>> data) {
        List<PromotionMail> mailList = new ArrayList<PromotionMail>();
        for (HashMap<String, String> map : data) {
            email.setToAddress(parseToAddress(map));
            email.setMessage(parseMessage(map, email));
            email.setSubject("您关注的产品降价了");
            mailList.add(email);
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
