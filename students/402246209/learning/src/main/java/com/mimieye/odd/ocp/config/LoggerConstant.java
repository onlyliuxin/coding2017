package com.mimieye.odd.ocp.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pierreluo on 2017/6/20.
 */
public class LoggerConstant {
    public static final int RAW_LOG = 1;
    public static final int RAW_LOG_WITH_DATE = 2;
    public static final int EMAIL_LOG = 1;
    public static final int SMS_LOG = 2;
    public static final int PRINT_LOG = 3;

    public static final Map<Integer, String> TPYE_MAP = new HashMap<>();
    public static final Map<Integer, String> METHOD_MAP = new HashMap<>();

    static {
        TPYE_MAP.put(1, "com.mimieye.odd.ocp.type.Impl.RawLogTypeImpl");
        TPYE_MAP.put(2, "com.mimieye.odd.ocp.type.Impl.RawLogWithDateTypeImpl");
        METHOD_MAP.put(1, "com.mimieye.odd.ocp.method.Impl.MailMethodImpl");
        METHOD_MAP.put(2, "com.mimieye.odd.ocp.method.Impl.SMSMethodImpl");
        METHOD_MAP.put(3, "com.mimieye.odd.ocp.method.Impl.PrintMethodImpl");
    }
}
