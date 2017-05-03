package com.johnChnia.coding2017.basic.stack.expr;

import com.johnChnia.coding2017.basic.List;
import com.johnChnia.coding2017.basic.stack.Stack;

public class PrefixExpr {
    String expr = null;

    public PrefixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {
        TokenParser tokenParser = new TokenParser();
        List<Token> tokens = tokenParser.parse(this.expr);
        Operator operator = new Operator();
        Stack<Float> stack = new Stack<>();
        for (int i = tokens.size() - 1; i >= 0; i--) {
            operator.handlerToken("prefix", stack, tokens.get(i));
        }
        return stack.pop();
    }


}
