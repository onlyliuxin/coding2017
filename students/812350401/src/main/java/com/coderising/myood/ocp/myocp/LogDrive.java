package com.coderising.myood.ocp.myocp;

/**
 * Created by thomas_young on 24/6/2017.
 */
public class LogDrive {

    public static void main(String[] args) {
        LogFactory logFactory = new LogFactory();
        Logger logger;
        for (int i = 1; i<=2; i++) {
            for (int j = 1; j<=3; j++ ) {
                logger = logFactory.createLogger(i, j);
                logger.log("haha");
            }
        }
    }
}
