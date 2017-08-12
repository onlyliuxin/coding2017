package dp.decorator;

/**
 * 外部邮件
 * Created by lx on 2017/7/29.
 */
public class OutsideEmailImpl extends FilterEmail {

    public OutsideEmailImpl(Email email) {
        super(email);
    }

    @Override
    public String getContent() {
        return super.getContent() + addSuffix();
    }

    public String addSuffix() {
        return "本邮件仅为个人观点，并不代表公司立场";
    }

}
