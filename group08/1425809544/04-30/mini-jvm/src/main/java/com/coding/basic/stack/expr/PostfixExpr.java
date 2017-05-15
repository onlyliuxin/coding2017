package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

/**
 * 后缀求值表达式
 *
 * @author xyy
 * @create 2017-04-25 17:45
 **/

public class PostfixExpr {
    String expr = null;

    public PostfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        TokenParser parser = new TokenParser();
        List<Token> tokens = parser.parse(this.expr);


        Stack<Float> numStack = new Stack<>();
        for (Token token : tokens) {
            if (token.isNumber()) {
                numStack.push(new Float(token.getIntValue()));
            } else {
                Float f2 = numStack.pop();
                Float f1 = numStack.pop();
                numStack.push(calculate(token.toString(), f1, f2));
            }
        }
        return numStack.pop().floatValue();
    }

    /**
     * @Author xuyangyang
     * @Describe 计算数值
     * @Date 2017/4/25
     * @Params
     * @Return
     */
    private Float calculate(String op, Float f1, Float f2) {
        if (op.equals("+")) {
            return f1 + f2;
        }
        if (op.equals("-")) {
            return f1 - f2;
        }
        if (op.equals("*")) {
            return f1 * f2;
        }
        if (op.equals("/")) {
            return f1 / f2;
        }
        throw new RuntimeException(op + " is not supported");
    }
}

