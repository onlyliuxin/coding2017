package com.coderising.myood.ocp.myocp;

public class FormatterFactory {
    public final int RAW_LOG = 1;
    public final int RAW_LOG_WITH_DATE = 2;

    public Formatter createFormatter(int logType) {
        Formatter formatter;
        switch (logType) {
            case RAW_LOG:
                formatter = new RawFormatter();
                break;
            case RAW_LOG_WITH_DATE:
                formatter = new DateFormatter();
                break;
            default:
                throw new IllegalArgumentException("Invalid logType " + logType);
        }
        return formatter;
    }
}