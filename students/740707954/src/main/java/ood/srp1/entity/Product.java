package ood.srp1.entity;

/**
 * 产品信息
 * Created by Administrator on 2017/6/15 0015.
 */
public class Product {
    private String productId = null;
    private String productDesc = null;

    public Product() {

    }

    public Product(String productId, String productDesc) {
        this.productId = productId;
        this.productDesc = productDesc;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
