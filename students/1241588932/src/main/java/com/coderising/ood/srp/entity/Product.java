package com.coderising.ood.srp.entity;

import lombok.Builder;
import lombok.Data;

/**
 * Created by Enan on 17/6/14.
 */
@Data
@Builder
public class Product {

    private String productID;
    private String productDesc;
}
