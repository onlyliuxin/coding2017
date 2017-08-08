package decorator;

public class ExternalEmail extends EmailDecorator {

    private Email email;

    public ExternalEmail(Email email) {
        this.email = email;
    }

    public String getContent() {
        return email.getContent() + "\n\n本邮件仅为个人观点，并不代表公司立场";
    }
}
