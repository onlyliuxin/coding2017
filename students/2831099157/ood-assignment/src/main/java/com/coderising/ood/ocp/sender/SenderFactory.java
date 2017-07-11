package com.coderising.ood.ocp.sender;

import com.coderising.ood.ocp.formatter.Formatter;
import com.coderising.ood.ocp.formatter.OnlyStringFormatter;
import com.coderising.ood.ocp.formatter.WithCurrentDateFormatter;

/**
 * Created by Iden on 2017/6/21.
 */
public class SenderFactory {

    public static final int ENAIL = 1;
    public static final int SMS = 2;
    public static final int PRINT = 3;


    public static Sender createSender(int type) {
        if (type == ENAIL) {
            return new EmailSender();
        }
        if (type == SMS) {
            return new SMSSender();
        }
        if (type == PRINT) {
            return new PrintSender();
        }
        return null;
    }

}
