package com.coderising.ood.ocp;

import com.coderising.ood.ocp.formatter.FormatterFactory;
import com.coderising.ood.ocp.sender.SenderFactory;

/**
 * Created by Iden on 2017/6/21.
 */
public class MainTest {
    public static void main(String[] args) {

        Logger logger = new Logger(FormatterFactory.createFormatter(FormatterFactory.ONLY_STRING),
                SenderFactory.createSender(SenderFactory.ENAIL));
        logger.log("Messge 1");

        Logger logger2 = new Logger(FormatterFactory.createFormatter(FormatterFactory.WITH_CURRENT_DATA),
                SenderFactory.createSender(SenderFactory.SMS));
        logger2.log("Messge 2");
    }
}
