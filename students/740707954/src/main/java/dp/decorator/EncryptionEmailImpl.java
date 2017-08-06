package dp.decorator;

/**
 * 加密邮件
 * Created by lx on 2017/7/29.
 */
public class EncryptionEmailImpl extends FilterEmail{

    public EncryptionEmailImpl(Email email) {
        super(email);
    }

    @Override
    public String getContent() {
        return encryptionEmail(super.getContent());
    }

    public String encryptionEmail(String content) {
        System.out.println("加密邮件。。");
        return "加密：" + content;
    }
}
