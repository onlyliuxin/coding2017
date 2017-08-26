package interpreter;

/**
 * Created by Lu on 2017/08/13.
 * @author Lu Mingming
 */
public class EmailLogger extends Logger {

    public EmailLogger(int mode) {
        this.mode = mode;
    }

    @Override
    public void message(String msg, int mode) {
        msg = "EmailLogger#" + msg;
        if (mode == this.mode) {
            System.out.println(msg);
            return;
        }
        if (nextLogger != null) {
            nextLogger.message(msg, mode);
        }
    }
}
