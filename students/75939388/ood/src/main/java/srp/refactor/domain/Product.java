package srp.refactor.domain;

/**
 * Created by Tee on 2017/6/16.
 */
public class Product {
    private String productId;
    private String productDesc;

    public Product(String productId, String productDesc){
        this.productId = productId;
        this.productDesc = productDesc;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
}
