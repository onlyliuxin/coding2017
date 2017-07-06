package com.coderising.ood.ocp;

/**
 * @author nvarchar
 *         date 2017/6/28
 */
public class PrintSender implements Sender {
    @Override
    public void send(String logMsg) {
        System.out.println(logMsg);
    }
}
