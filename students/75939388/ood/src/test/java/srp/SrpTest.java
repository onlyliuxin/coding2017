package srp;

import org.junit.Before;
import org.junit.Test;
import srp.refactor.PromotionMail;
import srp.refactor.util.FileUtil;

import java.io.File;
import java.util.List;

/**
 * Created by Tee on 2017/6/15.
 */
public class SrpTest {

    PromotionMail promotionMail = null;

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

        PromotionMail promotionMail = new PromotionMail();
        promotionMail.batchSetMails(data);
        promotionMail.send();
    }
}
