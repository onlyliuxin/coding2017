package com.johnChnia.coding2017.basic.stack.expr;

import com.johnChnia.coding2017.basic.stack.Stack;

/**
 * Created by john on 2017/4/20.
 */
public class Operator {

    public void handlerToken(String fix, Stack<Float> stack, Token token) {
        if (token.isNumber()) {
            stack.push(Float.parseFloat(token.toString()));
        } else if (token.isOperator()) {
            float p = stack.pop();
            float q = stack.pop();
            stack.push(perform(fix, token.toString(), p, q));
        }
    }


    private float perform(String fix, String operator, float p, float q) {
        float result = 0.0f;
        if (operator.equals("+")) {
            result = p + q;
        } else if (operator.equals("-")) {
            if (fix.equals("postfix")) {
                result = q - p;
            } else if (fix.equals("prefix")) {
                result = p - q;
            }
        } else if (operator.equals("*")) {
            result = p * q;
        } else if (operator.equals("/")) {
            if (fix.equals("postfix")) {
                result = q / p;
            } else if (fix.equals("prefix")) {
                result = p / q;
            }
        }
        return result;
    }
}
