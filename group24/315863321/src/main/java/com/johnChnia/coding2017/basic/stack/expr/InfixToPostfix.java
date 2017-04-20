package com.johnChnia.coding2017.basic.stack.expr;


import com.johnChnia.coding2017.basic.ArrayList;
import com.johnChnia.coding2017.basic.List;
import com.johnChnia.coding2017.basic.stack.Stack;


/***
 * Rule:
 */
public class InfixToPostfix {

    public static List<Token> convert(String expr) {
        TokenParser tokenParser = new TokenParser();
        List<Token> tokens = tokenParser.parse(expr);
        List<Token> list = new ArrayList<>();
        Stack<Token> stack = new Stack<>();
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token.isNumber()) {
                list.add(token);
            } else if (token.isOperator()) {
                while (!stack.empty() && !token.hasHigherPriority(stack.peek())) {
                        list.add(stack.pop());
                }
                stack.push(token);

            }
        }
        while (!stack.empty()) {
            list.add(stack.pop());
        }
        return list;
    }

}
