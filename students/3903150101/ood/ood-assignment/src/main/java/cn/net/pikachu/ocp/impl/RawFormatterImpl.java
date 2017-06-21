package cn.net.pikachu.ocp.impl;

import cn.net.pikachu.ocp.LogFormatter;

import java.util.*;

/**
 * 不改变原格式
 */
public class RawFormatterImpl implements LogFormatter {

    /**
     * Default constructor
     */
    public RawFormatterImpl() {
    }

    /**
     * @param msg 
     * @return
     */
    public String format(String msg) {
        // TODO implement here
        return "";
    }

}