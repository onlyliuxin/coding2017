package work01.srp;


public class Product {

    protected String productID = null;


    protected String productDesc = null;


    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductDesc(String desc) {
        this.productDesc = desc;
    }


    public String getProductDesc() {
        return productDesc;
    }

    public String getProductID() {
        return productID;
    }
}
