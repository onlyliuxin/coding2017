package interpreter;

/**
 * Created by Lu on 2017/08/13.
 * @author Lu Mingming
 */
public abstract class Logger {

    public static final int DEBUG = 1;
    public static final int NOTICE = 2;
    public static final int ERR = 3;

    protected int mode;
    protected Logger nextLogger;

    public abstract void message(String msg, int mode);

    public Logger setNext(Logger logger) {
        nextLogger = logger;
        return this;
    }
}
