package com.ood.srp.promotion;

import com.ood.srp.product.ProductDetail;
import com.ood.srp.user.UserInfo;

import java.util.List;

/**
 * 促销处理类
 * Created by ajaxfeng on 2017/6/20.
 */
public interface PromotionService {
    /**
     * 获取产品信息
     * @return
     */
    List<ProductDetail> getPromotionProduct();

    /**
     * 获取用户信息
     * @return
     */
    List<UserInfo> getPromotionUserInfo();

    /**
     * 发送促销信息
     * @return
     */
    List<String> sendPromotionInfo();

}
