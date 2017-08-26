package dp.ChainOfResponsibility;

/**
 * Created by lx on 2017/8/12.
 */
public interface Logger {

    public static final int DEBUG = 3;
    public static final int NOTICE = 2;
    public static final int ERR = 1;

    Logger setNext(Logger logger);

    void message(String content, int level);
}
