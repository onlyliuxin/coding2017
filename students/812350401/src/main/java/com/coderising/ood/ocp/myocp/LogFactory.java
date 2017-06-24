package com.coderising.ood.ocp.myocp;


/**
 * Created by thomas_young on 24/6/2017.
 */
public class LogFactory {
    public final int RAW_LOG = 1;
    public final int RAW_LOG_WITH_DATE = 2;
    public final int EMAIL_LOG = 1;
    public final int SMS_LOG = 2;
    public final int PRINT_LOG = 3;

    public Logger createLogger(int logType, int logMethod) {
        Msg msg = genMsg(logType);
        Method method = genMethod(logMethod);
        return new Logger(msg, method);
    }

    private Method genMethod(int logMethod) {
        Method method;
        switch (logMethod) {
            case EMAIL_LOG:
                method = new EmailMethod();
                break;
            case SMS_LOG:
                method = new SmsMethod();
                break;
            case PRINT_LOG:
                method = new PrintMethod();
                break;
            default:
                throw new IllegalArgumentException("Invalid logMethod " + logMethod);
        }
        return method;
    }

    private Msg genMsg(int logType) {
        Msg msg;
        switch (logType) {
            case RAW_LOG:
                msg = new RawMsg();
                break;
            case RAW_LOG_WITH_DATE:
                msg = new DateMsg();
                break;
            default:
                throw new IllegalArgumentException("Invalid logType " + logType);
        }
        return msg;
    }
}
