package com.aaront.exercise.basic.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {

    public static List<Token> convert(String expr) {
        TokenParser parser = new TokenParser();
        List<Token> tokens = parser.parse(expr);
        List<Token> reversePolish = new ArrayList<>();
        Stack<Token> stack = new Stack<>();
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token.isNumber()) reversePolish.add(token);
            else {
                if(stack.isEmpty()) stack.push(token);
                else {
                    Token topToken = stack.peek();
                    while (!token.hasHigherPriority(topToken)) {
                        reversePolish.add(stack.pop());
                        if(stack.isEmpty()) break;
                        topToken = stack.peek();
                    }
                    stack.push(token);
                }
            }
        }
        while (!stack.isEmpty()) {
            reversePolish.add(stack.pop());
        }
        return reversePolish;
    }
}
