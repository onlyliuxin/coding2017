package cn.net.pikachu.ocp;

import java.util.*;

/**
 * 日志格式
 */
public interface LogFormatter {

    /**
     * @param msg 
     * @return
     */
    public String format(String msg);

}