package com.ood.srp.product;

import java.util.List;

/**
 * 产品信息
 * Created by ajaxfeng on 2017/6/20.
 */
public interface ProductDetailService {
    /**
     * 获取产品详情
     *
     * @param lineList
     * @return
     */
    public List<ProductDetail> getProductDetailList(List<String> lineList);
}
