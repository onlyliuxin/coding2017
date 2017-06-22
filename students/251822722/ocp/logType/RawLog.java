package ocp.logType;

/**
 * ocp.ocp.logType
 * Created by Eric Wang on 6/21/17.
 */
public class RawLog implements LogType{

    private  String logMsg;

    @Override
    public void setMessage(String message) {
        logMsg = message;

    }

    @Override
    public String getMessage() {
        return logMsg;
    }
}
