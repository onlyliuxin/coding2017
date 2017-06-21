package cn.net.pikachu.ocp;

import java.util.*;

/**
 * 日志显示方式
 */
public interface LogSender {

    /**
     * @param log
     */
    public void output(String log);

}