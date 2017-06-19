package cn.net.pikachu.base;

import cn.net.pikachu.Configuration;
import cn.net.pikachu.ConfigurationKeys;
import cn.net.pikachu.domain.Product;
import cn.net.pikachu.domain.User;

import java.util.HashMap;
import java.util.Map;

public class PromotionMail extends Mail {
    public PromotionMail(User user, Product product) {
        super();
        Configuration configuration = new Configuration();
        this.mailSetting.setSmtpHost(ConfigurationKeys.SMTP_SERVER);
        this.mailSetting.setAltSmtpHost(configuration.getProperty(ConfigurationKeys.ALT_SMTP_SERVER));
        this.mailSetting.setFromAddress(configuration.getProperty(ConfigurationKeys.SMTP_SERVER));
        this.mailSetting.setSubject("您关注的产品降价了");
        this.mailSetting.setToAddress(user.getEmail());

        Map<String,Object> params = new HashMap<>();
        params.put("username",user.getUsername());
        params.put("productName",product.getName());
        this.mailContentTemplate = new PromotionMailContentTemplate(params);
    }
}
