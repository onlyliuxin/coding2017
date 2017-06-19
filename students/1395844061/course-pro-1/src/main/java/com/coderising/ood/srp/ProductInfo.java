package com.coderising.ood.srp;

/**
 * ProductInfo
 *
 * @author Chenpz
 * @package com.coderising.ood.srp
 * @date 2017/6/14/22:41
 */
public class ProductInfo {

    private String productID = null;
    private String productDesc = null;

    public ProductInfo(){}

    public ProductInfo(String productID, String productDesc){
        this.productID = productID;
        this.productDesc = productDesc;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public String getProductID() {
        return productID;
    }

}
