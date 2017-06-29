package com.thomsom.coderising.ood.srp;

/**
 * Product entity class.
 *
 * @author Thomson Tang
 * @version Created: 23/06/2017.
 */
public class Product {
    private String productId;
    private String productName;

    private Product() {
    }

    private Product(String productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public static Product newInstance(String productId, String productName) {
        return new Product(productId, productName);
    }

    public static Product newInstance() {
        return new Product();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
