package com.ood.srp.promotion.impl;

import com.ood.srp.file.FileService;
import com.ood.srp.mail.MailService;
import com.ood.srp.product.ProductDetail;
import com.ood.srp.product.ProductDetailService;
import com.ood.srp.promotion.PromotionService;
import com.ood.srp.user.UserInfo;
import com.ood.srp.user.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布促销信息
 * Created by yuxia on 2017/6/21.
 */
public class PromotionServiceImpl implements PromotionService {

    private static final String FILE_PATH = "pro.text";

    @Autowired
    FileService fileService;
    @Autowired
    ProductDetailService productDetailService;
    @Autowired
    UserInfoService userInfoService;
    @Autowired
    MailService mailService;

    /**
     * <h1>促销</h1><br>
     * 1. 获取文件内容<br>
     * 2. 根据文件内容，获取商品信息<br>
     * 3. 根据商品信息，获得用户信息<br>
     * 4. 针对用户信息，进行邮件发送<br>
     * 可扩展行：
     *  1. 发送促销信息，可抽象一个接口：具体可以通过邮件、短信等手段发送
     * @return
     * @throws Exception
     */
    @Override
    public String promotion() throws Exception {
        List<String> strings = fileService.readFile(FILE_PATH);
        List<ProductDetail> productDetailList =
                productDetailService.getProductDetailList(strings);
        List<String> idList = productDetailList2IDList(productDetailList);
        List<UserInfo> userInfoList = userInfoService.listUserInfo(idList);
        String s = mailService.sendEmail(userInfoList);
        System.out.println(s);
        return s;
    }

    List<String> productDetailList2IDList(List<ProductDetail> productDetails) {
        List<String> idList = new ArrayList<>();
        for (ProductDetail productDetail : productDetails) {
            idList.add(productDetail.getId());
        }
        return idList;
    }

}
