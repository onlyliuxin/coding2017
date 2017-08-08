package dp.decorator;

/**
 * Created by lx on 2017/7/29.
 */
public class EmailImpl implements Email {
    private String content;

    public EmailImpl(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}
