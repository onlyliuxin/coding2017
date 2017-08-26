package interpreter;

/**
 * Created by Lu on 2017/08/13.
 * @author Lu Mingming
 */
public class LoggerTest {

    public static void main(String[] args) {
        Logger logger = new StdoutLogger(Logger.DEBUG).setNext(
                new EmailLogger(Logger.NOTICE).setNext(
                        new FileLogger(Logger.ERR)));

        logger.message("进入计算函数", Logger.DEBUG);
        logger.message("第一步已经完成", Logger.NOTICE);
        logger.message("一个致命的错误发成了", Logger.ERR);
    }

}
