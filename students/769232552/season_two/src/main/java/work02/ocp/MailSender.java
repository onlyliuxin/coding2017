package work02.ocp;

public class MailSender implements Sender {
    public void send(String msg) {
        MailUtil.send(msg);
    }
}
