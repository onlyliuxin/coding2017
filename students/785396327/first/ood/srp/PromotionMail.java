package first.ood.srp;

public class PromotionMail extends Email {
    private String productID;
    private String productDesc;

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getproductID() {
        return productID;
    }

    public void setProductDesc(String desc) {
        this.productDesc = desc;
    }

    public String getProductDesc() {
        return this.productDesc;
    }
}
