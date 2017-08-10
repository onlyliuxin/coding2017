package com.coderising.mydp.responseChain;

/**
 * Created by thomas_young on 11/8/2017.
 */
public class Client {

    public static void main(String[] args) {
        AbstractLogger logger = new StdoutLogger()
                .setNext(new EmailLogger()
                .setNext(new FileLogger()));

        // 由StdoutLogger处理
        logger.message("进入计算函数", AbstractLogger.DEBUG);
        // 由StdoutLogger和EmailLogger处理
        System.out.println("*****************");
        logger.message("第一步已完成", AbstractLogger.NOTICE);
        // 由所有logger处理
        System.out.println("*****************");
        logger.message("一个致命的错误发生了", AbstractLogger.ERR);
    }

}
