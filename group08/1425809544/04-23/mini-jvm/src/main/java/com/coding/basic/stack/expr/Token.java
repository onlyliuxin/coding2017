package com.coding.basic.stack.expr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xyy
 *  分解表达式，存入
 * @create 2017-04-19 13:38
 **/
public class Token {

    public static final List<String> OPERATORS = Arrays.asList("+", "-", "*", "/");
    //先序
    private static Map<String, Integer> priorities = new HashMap<>();

    static {
        priorities.put("+",1);
        priorities.put("-", 1);
        priorities.put("*",2);
        priorities.put("/", 2);
    }

    static final int OPERATOR = 1;
    static final int NUMBER = 2;
    int type;
    String value;

    public Token(int type, String value) {
        this.type = type;
        this.value = value;
    }

    public boolean isOperator() {
        return type == OPERATOR;
    }

    public boolean isNumber() {
        return type == NUMBER;
    }

    public int getIntValue() {
        return Integer.valueOf(value).intValue();
    }

    public String toString() {
        return value;
    }

    /**
     * 判断优先级
     * @param token
     * @return
     */
    public boolean hasHigherPriority(Token token) {
        if (!this.isOperator() && !token.isOperator()) {
            throw new RuntimeException("数字不能够比较优先级!");
        }
        return priorities.get(this.value) - priorities.get(token.value) > 0;
    }



}
