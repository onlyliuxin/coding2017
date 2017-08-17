package com.coderising.ood.ocp;

/**
 * @author nvarchar
 *         date 2017/6/28
 */
public class SenderFactory {

    public static Sender createSenderFormat(int method) {
        if (method == 1) {
            return new MailSender();
        } else if (method == 2) {
            return new SMSSender();
        } else if (method == 3) {
            return new PrintSender();
        }
        return null;
    }
}
