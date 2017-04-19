package com.johnChnia.coding2017.basic.stack.expr;

import com.johnChnia.coding2017.basic.List;
import com.johnChnia.coding2017.basic.stack.Stack;

public class PostfixExpr {
    String expr = null;

    public PostfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        TokenParser tokenParser = new TokenParser();
        List<Token> tokens = tokenParser.parse(this.expr);
        Operator operator = new Operator();
        Stack<Float> stack = new Stack<>();
        for (int i = 0; i < tokens.size(); i++) {
            operator.handlerToken("postfix", stack, tokens.get(i));

        }

        return stack.pop();
    }


}
