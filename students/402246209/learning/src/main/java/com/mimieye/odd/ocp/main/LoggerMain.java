package com.mimieye.odd.ocp.main;

import com.mimieye.odd.ocp.config.LoggerConstant;
import com.mimieye.odd.ocp.logger.Impl.LoggerImpl;
import com.mimieye.odd.ocp.logger.LoggerInterface;

/**
 * Created by Pierreluo on 2017/6/20.
 */
public class LoggerMain {
    public static void main(String[] args) {
        try {
            LoggerInterface logger = new LoggerImpl(LoggerConstant.RAW_LOG, LoggerConstant.EMAIL_LOG);
            String msg = "log content.";
            logger.log(msg);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
