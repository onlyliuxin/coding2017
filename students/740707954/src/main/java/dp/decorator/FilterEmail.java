package dp.decorator;

/**
 * Created by lx on 2017/7/29.
 */
public class FilterEmail implements Email {

    private Email email;

    public FilterEmail(Email email) {
        this.email = email;
    }

    @Override
    public String getContent() {
        return email.getContent();
    }
}
