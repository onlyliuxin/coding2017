package com.coderising.myood.ocp.myocp;

public class SenderFactory {
    public final int EMAIL_LOG = 1;
    public final int SMS_LOG = 2;
    public final int PRINT_LOG = 3;

    public Sender createSender(int logMethod) {
        Sender sender;
        switch (logMethod) {
            case EMAIL_LOG:
                sender = new EmailSender();
                break;
            case SMS_LOG:
                sender = new SmsSender();
                break;
            case PRINT_LOG:
                sender = new PrintSender();
                break;
            default:
                throw new IllegalArgumentException("Invalid logMethod " + logMethod);
        }
        return sender;
    }
}