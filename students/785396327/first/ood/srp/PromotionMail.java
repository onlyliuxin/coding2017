package first.ood.srp;

public class PromotionMail extends Email {
    private String productID = null;
    private String productDesc = null;

    public PromotionMail(Email email) {
        super(email.smtpHost, email.altSmtpHost, email.fromAddress, email.toAddress, email.subject, email.message);
    }

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
