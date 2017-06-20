package work01.srp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Mail {

    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";


    private Product product;
    private String toAddress;
    private String subject;
    private String message;
    private String sendMailQuery;


    public Mail(Product product){
        this.product = product;
    }

    public List loadMailingList() throws Exception {
        return DBUtil.query(this.sendMailQuery);
    }

    public void generateMail(HashMap userInfo) throws IOException {
        toAddress = (String) userInfo.get(EMAIL_KEY);
        if (toAddress.length() > 0){
            setMessage(userInfo);
            setLoadQuery();
            setToAddress(toAddress);
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(HashMap userInfo) throws IOException {
        String name = (String) userInfo.get(NAME_KEY);
        this.message = "尊敬的 "+name+", 您关注的产品 " + product.getProductDesc() + " 降价了，欢迎购买!" ;
        this.subject = "您关注的产品降价了";
    }

    public String getSubject() {
        return subject;
    }

    public void setLoadQuery()  {
        this.sendMailQuery = "Select name from subscriptions "
                + "where product_id= '" + product.getProductID() +"' "
                + "and send_mail=1 ";
    }


    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
