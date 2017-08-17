package com.coderising.ood.ocp;

/**
 * @author nvarchar
 *         date 2017/6/28
 */
public class RawLogFactory {
    public static LogMsgConvert createFormatter(int type) {
        if (type == 1) {
            return new RawLog();
        }
        if (type == 2) {
            return new RawLogWtihDate();
        }
        return null;
    }
}
