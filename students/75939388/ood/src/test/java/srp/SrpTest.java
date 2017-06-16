package srp;

import org.junit.Before;
import org.junit.Test;
import srp.refactor.PromotionMailClient;
import srp.refactor.domain.Product;
import srp.refactor.services.ProductService;
import srp.refactor.util.FileUtil;

import java.io.File;
import java.util.List;

/**
 * Created by Tee on 2017/6/15.
 */
public class SrpTest {

    PromotionMailClient promotionMail = null;

    private static int in = 0;

    @Before
    public void init(){

    }

    /**
     * 重构后的代码尝试运行
     */
    @Test
    public void runTrial() throws Exception{
        File file = new File("/Users/Tee/Code/learning2017/season2/coding2017/" +
                "students/75939388/ood/src/main/resources/ood_demo_file/product_promotion.txt");
        List<String> data = FileUtil.readFile(file);

        PromotionMailClient promotionMail = new PromotionMailClient();
        List<Product> promotionProducts = new ProductService().getPromotionInfoList(data);
        promotionMail.batchWrite(promotionProducts);
        promotionMail.batchSend(false);
    }
}
