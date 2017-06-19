package first.ood.srp;

/**
 * Created by william on 2017/6/14.
 */
public class PromotionFileParser extends FileParser {


    public PromotionFileParser(String filePath) {
        super(filePath);
    }

    @Override
    protected void parseInfoFromFile(Email email) {
        PromotionMail promotionMail = (PromotionMail) email;
        promotionMail.setProductID(parseProductID());
        promotionMail.setProductDesc(parseProductDesc());
    }

    private String parseProductID() {
        return super.data[0];
    }

    private String parseProductDesc() {
        return super.data[1];
    }
}
