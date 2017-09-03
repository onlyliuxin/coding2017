package interpreter;

/**
 * Created by Lu on 2017/08/13.
 * @author Lu Mingming
 */
public class StdoutLogger extends Logger {

    public StdoutLogger(int mode) {
        this.mode = mode;
    }

    @Override
    public void message(String msg, int mode) {
        msg = "StdoutLogger#" + msg;
        if (mode == this.mode) {
            System.out.println(msg);
            return;
        }
        if (nextLogger != null) {
            nextLogger.message(msg, mode);
        }
    }


}
