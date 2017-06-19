package com.coderising.ood.srp;

import com.coderising.ood.srp.utils.ArgsUtil;
import com.coderising.ood.srp.utils.FileUtil;

import java.util.List;

/**
 * MainTest
 *
 * @author Chenpz
 * @package com.coderising.ood.srp
 * @date 2017/6/14/22:45
 */
public class MainTest {

    public static void main(String[] args) throws Exception{
        String filePath = MainTest.class.getClassLoader().getResource("product_promotion.txt").getFile();
        List<ProductInfo> productInfoList = FileUtil.readProductInfoFromFile(filePath);
        if (ArgsUtil.isNotEmpty(productInfoList)){
            for (ProductInfo productInfo: productInfoList){
                new PromotionMail(productInfo)
                        .sendEMails(false);
            }
        }
    }
}
