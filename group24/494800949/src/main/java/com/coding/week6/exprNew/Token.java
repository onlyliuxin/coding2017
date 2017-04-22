package com.coding.week6.exprNew;

/**
 * Created by Administrator on 2017/4/22 0022.
 */
public class Token {

    private int type;
    private String value;

    static final int NUMBER = 1;
    static final int OPERATOR = 2;
    public Token(int type, String value) {
        this.type = type;
        this.value = value;
    }


    public boolean isNumber(){
        return type == NUMBER;
    }

    public boolean isOperator(){
        return type == OPERATOR;
    }

    public int getIntValue() {
        if (isNumber())
            return Integer.valueOf(value);
        else
            throw new RuntimeException("not a number");
    }

    public String toString() {
        return type+":"+value;
    }


    public Operator getOperator() {
        if (isOperator()) {
            return Operator.getOperatorMap().get(value);
        } else {
            throw new RuntimeException("not a operator");
        }
    }


}
