package ood.work.srp;

import java.io.IOException;

/**
 * Created by Administrator on 2017/6/17 0017.
 */
public class PromotionMail {

    private String path;

    public PromotionMail(String path) {
        this.path = path;
    }

    public void execute() throws IOException {
        SMTPClient client = new SMTPClient();
        client.sendEmails(false, Emails.createEmails(path));
    }



    public static void main(String[] args) {
        String path = "I:\\sourceCode\\coding2017_2\\students\\494800949\\src\\main\\java\\ood\\assignment\\srp\\product_promotion.txt";
        PromotionMail mail = new PromotionMail(path);
        try {
            mail.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
