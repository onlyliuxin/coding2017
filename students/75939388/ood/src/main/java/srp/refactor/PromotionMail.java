package srp.refactor;

import org.apache.commons.lang3.StringUtils;
import srp.refactor.configuration.Configuration;
import srp.refactor.configuration.ConfigurationKeys;
import srp.refactor.mail.Mail;
import srp.refactor.util.DBUtil;

import java.util.HashMap;
import java.util.List;

/**
 * 在原有的Mail邮件功能的基础上修改而来的促销邮件发送端
 *
 * Created by Tee on 2017/6/15.
 */
public class PromotionMail extends Mail {

    protected String productID = null;
    protected String productDesc = null;
    protected String sendMailQuery = null;

    private static Configuration config = new Configuration();

    private final static String NAME_KEY = "NAME";
    private final static String EMAIL_KEY = "EMAIL";

    private void init(){
        super.initMailSettings(
                config.getProperty(ConfigurationKeys.SMTP_SERVER),
                config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER),
                config.getProperty(ConfigurationKeys.EMAIL_ADMIN)
        );
    }

    public PromotionMail(){
        init();
    }

    @Override
    public void createMail(String toAddress, String subject, String message){
        this.toAddress = toAddress;
        this.subject = subject;
        this.message = message;
        super.write();
    }

    public void setProductPromotion(String productID, String productDesc) throws Exception{
        this.productID = productID;
        this.productDesc = productDesc;
    }

    /**
     * 模拟数据库查询
     */
    protected void setLoadQuery() throws Exception {
        if(StringUtils.isBlank(productID)){
            throw new RuntimeException("没有获取到productID");
        }

        sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + productID +"' "
                + "and send_mail=1 ";

        System.out.println("loadQuery set, productID -> " + productID);
    }

    public void batchSetMails(List<String> data) throws Exception{
        if(data.isEmpty()){
            throw new RuntimeException("data不能为空");
        }
        for(int i = 0; i < data.size(); i+=2){
            String productId = data.get(i);
            String productName = data.get(i+1);
            setProductPromotion(productId, productName);
            setLoadQuery();

            List<HashMap> userList = DBUtil.query(sendMailQuery);
            for(HashMap userInfo : userList){
                createMail(
                        (String)userInfo.get(EMAIL_KEY),
                        "您关注的" + productName + "已降价",
                        "尊敬的 "+ userInfo.get(NAME_KEY) +", 您关注的产品 " + productDesc + " 降价了，欢迎购买!"
                );
            }
        }
    }

    /**
     * 暂时这么实现
     */
    @Override
    public void send(){
        super.batchSend(false);
    }
}