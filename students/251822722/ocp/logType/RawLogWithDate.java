package ocp.logType;

import ocp.util.DateUtil;

/**
 * ocp.ocp.logType
 * Created by Eric Wang on 6/21/17.
 */
public class RawLogWithDate implements LogType {


    private  String logMsg;

    @Override
    public void setMessage(String message) {

        String txtDate = DateUtil.getCurrentDateAsString();
        logMsg = txtDate + ": " + message;

    }

    @Override
    public String getMessage() {
        return logMsg;
    }
}
