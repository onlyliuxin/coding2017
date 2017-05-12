package com.pan.stack.expr;

import java.util.List;
import java.util.Stack;

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
                numStack.push(CalculateUtil.calculate(token.toString(), f1, f2));
            }
        }
        return numStack.pop().floatValue();
    }


}
