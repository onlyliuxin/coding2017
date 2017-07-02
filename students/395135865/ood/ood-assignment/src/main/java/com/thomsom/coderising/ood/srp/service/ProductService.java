package com.thomsom.coderising.ood.srp.service;

import com.thomsom.coderising.ood.srp.Product;

import java.util.List;

/**
 * 商品业务逻辑接口
 *
 * @author Thomson Tang
 * @version Created: 23/06/2017.
 */
public interface ProductService {
    /**
     * 查询所有的商品
     *
     * @return 商品列表
     * @throws Exception if error
     */
    List<Product> listProduct() throws Exception;

    /**
     * 根据用户查询该用户关注的商品
     *
     * @param userId 用户标示符
     * @return 用户关注的商品
     */
    List<Product> listSubscriptProduct(String userId);
}
