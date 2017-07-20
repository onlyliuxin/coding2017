package com.ood.srp.product.impl;

import com.ood.srp.product.ProductDetail;
import com.ood.srp.product.ProductDetailService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品信息逻辑
 * Created by yuxia on 2017/6/21.
 */
@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    /**
     * 获取商品信息
     *
     * @param lineList
     * @return
     */
    public List<ProductDetail> getProductDetailList(List<String> lineList) {
        List<ProductDetail> productDetailList = new ArrayList<>();
        lineList.forEach(line -> {
            String[] splitInfo = line.split(" ");
            if (splitInfo.length >= 2) {
                String id = splitInfo[0];
                String description = splitInfo[1];
                ProductDetail productDetail = getProductDetail(id, description);
                productDetailList.add(productDetail);
            }
        });
        return productDetailList;
    }

    private ProductDetail getProductDetail(String id, String description) {
        ProductDetail productDetail = new ProductDetail();
        productDetail.setId(id);
        productDetail.setDescription(description);
        return productDetail;
    }
}
