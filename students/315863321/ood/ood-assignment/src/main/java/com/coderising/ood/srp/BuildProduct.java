package com.coderising.ood.srp;

import java.util.List;

/**
 * Created by john on 2017/6/14.
 */
public class BuildProduct extends Build{
    private Product product;

    public BuildProduct(Product product) {
        this.product = product;
    }
    void build() {
        List data = reader.read();
        product.setProductID((String) data.get(0));
        product.setProductDesc((String) data.get(1));
    }


}
