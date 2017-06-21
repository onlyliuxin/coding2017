package ood.newSrp.server;

/**
 * Created by lx on 2017/6/17.
 */
public class TempSmtpFactory implements SmtpFactory {
    @Override
    public SmtpServer createSmtp() {
        return new TempSmtpServer();
    }
}
