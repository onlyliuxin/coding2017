package homework.jyz.coding2017;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 配置邮件信息类
 * Created by jyz on 2017/6/13.
 */
public class MailConfig {
    private MailDao dao;
    private static final String NAME_KEY = "NAME";
    private static final String EMAIL_KEY = "EMAIL";

    private String productID = "";
    private String productDesc = "";

    public MailConfig(MailDao dao) {
        this.dao = dao;
    }

    public List getEmails() {
        List<HashMap> mails = dao.getMails(productID);
        List<Email> list = new ArrayList<>();
        for (HashMap map : mails) {
            Email email = new Email();
            setMessage(map, email);
            list.add(email);
        }
        return list;
    }

    private void setMessage(HashMap userInfo, Email email) {
        String name = (String) userInfo.get(NAME_KEY);
        email.setSubject("您关注的产品降价了");
        String message = "尊敬的 " + name + ", 您关注的产品 " + productDesc + " 降价了，欢迎购买!";
        email.setMessage(message);
    }


    public  void readFile(File file) throws IOException // @02C
    {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String temp = br.readLine();
            String[] data = temp.split(" ");
            productID = data[0];
            productDesc =  data[1];
            System.out.println("产品ID = " + productID + "\n");
            System.out.println("产品描述 = " + productDesc + "\n");

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } finally {
            if(br != null){
                br.close();
            }
        }
    }
}
