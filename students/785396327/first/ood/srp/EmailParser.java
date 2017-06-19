package first.ood.srp;

import java.util.List;

/**
 * Created by william on 2017/6/12.
 */
public class EmailParser {
    private ConfigParser configParser;
    private FileParser fileParser;
    private DBParser dbParser;

    public EmailParser(ConfigParser configParser,FileParser fileParser,DBParser dbParser) {
        this.configParser = configParser;
        this.fileParser = fileParser;
        this.dbParser = dbParser;
    }

    public List<Email> parseEmailList() {
        PromotionMail promotionMail = new PromotionMail();
        configParser.parseInfoFromConfig(promotionMail);
        fileParser.parseInfoFromFile(promotionMail);
        return dbParser.parseInfoFromDB(promotionMail);
    }

}
