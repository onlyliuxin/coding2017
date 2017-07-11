package com.coderising.ood.ocp;

import com.coderising.ood.srp.Mail;

/**
 * Created by szf on 6/20/17.
 */
public class LogFactory {

    static ILog produce(int logMethod) {
        ILog log = null;
        switch (logMethod) {
            case Config.EMAIL_LOG:
                log = new MailUtil();
                break;
            case Config.SMS_LOG:
                log = new SMSUtil();
                break;
            case Config.PRINT_LOG:
                log = new PrintUtil();
                break;
        }
        return log;
    }
}
