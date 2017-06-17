package yangsongbao;

import yangsongbao.model.ProductInfo;
import yangsongbao.model.UserInfo;
import yangsongbao.persistence.ProductManager;
import yangsongbao.persistence.UserDao;

import java.util.List;

public class PromotionMailSender {

    private UserDao userDao;
    private CustomerNoticeService customerNoticeService;
    private String promotionProductFile;

    public PromotionMailSender(UserDao userDao, CustomerNoticeService customerNoticeService, String promotionProductFile) {
        this.userDao = userDao;
        this.customerNoticeService = customerNoticeService;
        this.promotionProductFile = promotionProductFile;
    }

	public void send(){

        List<ProductInfo> promotionPruduct = ProductManager.getPromotionPruductFromFile(promotionProductFile);
        for (ProductInfo productInfo : promotionPruduct){
            List<UserInfo> userInfos = userDao.queryUserNeedPromotionMail(productInfo);
            customerNoticeService.noticeByEmail(userInfos, NoticeConditionEnum.PROMOTION, productInfo);
        }
    }

    public static void main(String[] args) {

        String filePath = "D:\\project\\Learn\\coding2017-2\\students\\578505552\\ood\\ood-assignment\\src\\main\\java\\yangsongbao\\product_promotion.txt";
        UserDao userDao = new UserDao();
        CustomerNoticeService customerNoticeService = new CustomerNoticeService();

        PromotionMailSender promotionMailSender = new PromotionMailSender(userDao, customerNoticeService, filePath);
        promotionMailSender.send();
    }

}
