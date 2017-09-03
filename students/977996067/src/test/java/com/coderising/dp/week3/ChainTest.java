package com.coderising.dp.week3;

import com.coderising.dp.week3.responsibility.EmailLogger;
import com.coderising.dp.week3.responsibility.FileLogger;
import com.coderising.dp.week3.responsibility.Logger;
import com.coderising.dp.week3.responsibility.StdoutLogger;
import org.junit.Test;

public class ChainTest {

    @Test
    public void testLoggerChain() {
        Logger logger = new StdoutLogger(Logger.DEBUG)
                .setNext(new EmailLogger(Logger.NOTICE))
                .setNext(new FileLogger(Logger.ERR));

        logger.message("计入计算函数", Logger.DEBUG);
        logger.message("第一步已完成", Logger.NOTICE);
        logger.message("出现了一个致命的bug", Logger.ERR);
    }
}
