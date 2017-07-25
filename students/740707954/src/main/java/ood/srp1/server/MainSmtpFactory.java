package ood.srp1.server;

/**
 * Created by lx on 2017/6/17.
 */
public class MainSmtpFactory implements SmtpFactory {
    @Override
    public SmtpServer createSmtp() {
        return new MainSmtpServer();
    }
}
