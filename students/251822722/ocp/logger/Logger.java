package ocp.logger;

import ocp.logType.LogType;
import ocp.logType.RawLog;
import ocp.logType.RawLogWithDate;

public class Logger {

    public final int RAW_LOG = 1;
    public final int RAW_LOG_WITH_DATE = 2;
    public final int EMAIL_LOG = 1;
    public final int SMS_LOG = 2;
    public final int PRINT_LOG = 3;

    public Logger(){

    }



    public Logger getLogger(int logType, int logMethod) {

        LogType logTypeClass;
        Logger logger;


        switch (logType) {
            case RAW_LOG:
                logTypeClass = new RawLog();
                break;
            case RAW_LOG_WITH_DATE:
                logTypeClass = new RawLogWithDate();
                break;
            default:
                logTypeClass = new RawLog();

        }


        switch (logMethod) {
            case EMAIL_LOG:
                logger = new MailLogger(logTypeClass);
                break;
            case SMS_LOG:
                logger = new SMSLogger(logTypeClass);
                break;
            case PRINT_LOG:
                logger = new DateLogger(logTypeClass);
                break;
            default:
                logger = new MailLogger(logTypeClass);

        }

        return logger;


    }
}

