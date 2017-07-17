package com.coderising.ood.ocp.formatter;

/**
 * Created by Iden on 2017/6/21.
 */
public class FormatterFactory {

    public static final int ONLY_STRING = 1;
    public static final int WITH_CURRENT_DATA = 2;

    public static Formatter createFormatter(int type) {
        if (type == ONLY_STRING) {
            return new OnlyStringFormatter();
        }
        if (type == WITH_CURRENT_DATA) {
            return new WithCurrentDateFormatter();
        }
        return null;
    }

}
