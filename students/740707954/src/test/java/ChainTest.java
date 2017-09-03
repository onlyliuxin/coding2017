import dp.ChainOfResponsibility.EmailLogger;
import dp.ChainOfResponsibility.FileLogger;
import dp.ChainOfResponsibility.Logger;
import dp.ChainOfResponsibility.StdoutLogger;
import org.junit.Test;

/**
 * 责任链测试
 * Created by lx on 2017/8/12.
 */
public class ChainTest {
    private static Logger l = new StdoutLogger(Logger.DEBUG).setNext(new EmailLogger(Logger.NOTICE).setNext(new FileLogger(Logger.ERR)));

    @Test
    public void testDebug() {
        // StdoutLooger处理
        l.message("进入计算函数", Logger.DEBUG);
    }

    @Test
    public void testNotic() {
        // StdoutLogger和EmailLogger处理
        l.message("第一步已经完成", Logger.NOTICE);
    }

    @Test
    public void testErr() {
        // 三个都处理
        l.message("一个致命的错误发生了", Logger.ERR);
    }
}
