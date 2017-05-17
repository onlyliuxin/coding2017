package com.coding.week6.expr;

/**
 * Created by Administrator on 2017/4/16 0016.
 */
public class Token {

    private String symbol;

    public Token(String symbol) {
        this.symbol = symbol;
    }

    public boolean isNumber() {
        return symbol.matches("^\\d+$");
    }


    public boolean isOperator() {
        return symbol.matches("^[\\+|\\*|\\-|\\/]$");
    }

    public int parseInt() {
        return Integer.valueOf(symbol);
    }

    public char parseOperator() {
        return symbol.charAt(0);
    }


    public static boolean isOperator(char c) {
        return isAddOrSub(c) || isMulityOrDivide(c);
    }

    public static boolean isAddOrSub(char c) {
        return c == '+' || c == '-';
    }

    public static boolean isMulityOrDivide(char c) {
        return c == '/' || c == '*';
    }

    public static boolean isDigit(char c) {
        return  c >= '0' && c <= '9';
    }

    @Override
    public String toString(){
        return symbol;
    }
}
