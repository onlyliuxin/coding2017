package srp.refactor;

import srp.refactor.configuration.Configuration;
import srp.refactor.configuration.ConfigurationKeys;
import srp.refactor.domain.Product;
import srp.refactor.domain.User;
import srp.refactor.mail.MailClient;
import srp.refactor.services.ProductService;
import srp.refactor.services.UserService;

import java.util.List;

/**
 * 在原有的MailClient邮件功能的基础上修改而来的促销邮件发送端
 *
 * 根据SRP原则，这个邮件客户端只有两个职责：
 *     解析传进来的List<Product>，然后批量保存至超类的待发送邮件列表中
 *     全部解析完成(数据量较大时需要设置阈值)后
 *     由用户发起发送请求
 *
 * Created by Tee on 2017/6/15.
 */
public class PromotionMailClient extends MailClient {

    private static Configuration config = new Configuration();

    private UserService userService = new UserService();
    private ProductService productService = new ProductService();

    private void init(){
        super.initMailSettings(
                config.getProperty(ConfigurationKeys.SMTP_SERVER),
                config.getProperty(ConfigurationKeys.ALT_SMTP_SERVER),
                config.getProperty(ConfigurationKeys.EMAIL_ADMIN)
        );
    }

    public PromotionMailClient(){
        init();
    }

    @Override
    public void createMail(String toAddress, String subject, String message){
        this.toAddress = toAddress;
        this.subject = subject;
        this.message = message;
        super.addToMailList();
    }

    /**
     * 批量编写邮件
     * @param promotionProducts 促销中的所有产品
     * @throws Exception 查询sql时报的异常，这里选择不处理
     */
    public void batchWrite(List<Product> promotionProducts) throws Exception{
        if(promotionProducts.isEmpty()){
            throw new RuntimeException("没有商品待促销不能为空");
        }
        for(Product product : promotionProducts){
            String querySql = productService.getLoadQuerySql(product.getProductId());
            List<User> userList = userService.getUserList(querySql);
            for(User user : userList){
                String add = user.getEmail();
                String subj = "您关注的" + product.getProductDesc() + "已降价";
                String msg = "尊敬的 "+ user.getName() +", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!";

                createMail(add, subj, msg);
            }
        }
    }

}