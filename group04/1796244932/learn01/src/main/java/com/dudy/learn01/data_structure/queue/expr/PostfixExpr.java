package com.dudy.learn01.data_structure.queue.expr;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {
    String expr = null;

    public PostfixExpr(String expr) {
        this.expr = expr;
    }

    public float evaluate() {

        Stack<String> optStack = new Stack<>();
        Stack<Float> numStack = new Stack<>();

        TokenParser parser = new TokenParser();
        List<Token> tokens = parser.parse(expr);

        for (Token token : tokens) {
            if (token.isNumber()) {
                numStack.push(Float.parseFloat(token.value));
            } else if (token.isOperator()) {
                Float num2 = numStack.pop();
                Float num1 = numStack.pop();
                Float value = calculator(num1, num2, token.value);
                numStack.push(value);
            }
        }


        return numStack.pop();
    }

    private Float calculator(Float num1, Float num2, String op) {
        Float res = 0.0f;

        if ("*".equals(op)) {
            res = num1 * num2;
        } else if ("/".equals(op)) {
            res = num1 / num2;
        } else if ("-".equals(op)) {
            res = num1 - num2;
        } else if ("+".equals(op)) {
            res = num1 + num2;
        } else {
            System.out.println("opt is not  support...");
        }

        return res;
    }


}