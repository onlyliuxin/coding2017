package com.coderising.ood.srp;

import java.util.List;

/**
 * the business service of product.
 *
 * @author Thomson Tang
 * @version Created: 23/06/2017.
 */
public interface ProductService {
    List<Product> listProduct() throws Exception;
}
