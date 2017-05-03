package com.coding.week6.exprNew;

/**
 * Created by Administrator on 2017/4/22 0022.
 */
public class Token {

    private int    type;
    private String value;

    static final int NUMBER   = 1;
    static final int OPERATOR = 2;
    static final int LEFT_BRACKET  = 3;
    static final int RIGHT_BRACKET  = 4;
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

    public boolean isLeftBracket() {
        return type == LEFT_BRACKET;
    }

    public boolean isRightBracket() {
        return type == RIGHT_BRACKET;
    }

    public float getFloatValue() {
        if (isNumber())
            return Integer.valueOf(value);
        else
            throw new RuntimeException("not a number");
    }

    public String toString() {
        return value;
    }


    public Operator getOperator() {
        if (isOperator()) {
            return Operator.getOperatorMap().get(value);
        } else {
            throw new RuntimeException("not a operator");
        }
    }


    public String getValue() {
        return value;
    }


}
