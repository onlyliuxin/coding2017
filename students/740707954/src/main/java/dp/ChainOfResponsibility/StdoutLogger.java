package dp.ChainOfResponsibility;

/**
 * Created by lx on 2017/8/12.
 */
public class StdoutLogger implements Logger {
    private Logger nextLogger;
    private int level;

    public StdoutLogger(int level) {
        this.level = level;
    }

    /**
     * 设置下一处理
     * @param logger
     * @return
     */
    @Override
    public Logger setNext(Logger logger) {
        nextLogger = logger;
        return this;
    }

    /**
     * 输出日志信息
     * @param content
     * @param level
     */
    @Override
    public void message(String content, int level) {
        System.out.println("StdoutLogger 处理....");

        if (this.level >= level) {
            System.out.println(content);
        }

        if (null != nextLogger) {
            nextLogger.message(content, level);
        }
    }
}
