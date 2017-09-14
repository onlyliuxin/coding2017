package com.coderings.dp.chain;


/**
 * @author nvarchar
 *         date 2017/8/11
 */
public class Client {

    public static void main(String[] args) {
        Logger logger = new StdoutLogger(Logger.DEBUG)
                .setNext(new EmailLogger(Logger.NOTICE).setNext(new FileLogger(Logger.ERR).setNext(new EmailLogger(Logger.DEBUG))));

        logger.message("进入计算函数", Logger.DEBUG);
        logger.message("第一步已经完成", Logger.NOTICE);
        logger.message("一个致命错误发生", Logger.ERR);
    }
}
