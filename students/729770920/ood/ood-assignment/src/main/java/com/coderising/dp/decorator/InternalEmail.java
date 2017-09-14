package decorator;

public class InternalEmail extends EmailDecorator {

    private Email email;

    public String getContent() {
        return email.getContent();
    }

}
