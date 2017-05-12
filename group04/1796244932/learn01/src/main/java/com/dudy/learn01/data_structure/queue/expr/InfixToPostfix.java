package com.dudy.learn01.data_structure.queue.expr;

import java.util.*;

public class InfixToPostfix {

    public static List<Token> convert(String expr) {

        List<Token> tokens = new ArrayList<>();

        Stack<Token> optStack = new Stack<>();
        TokenParser parser  = new TokenParser();
        List<Token> list = parser.parse(expr);
        for (int i = 0;i < list.size(); i++){
            Token token = list.get(i);
            if(token.isNumber()){
                tokens.add(token);continue;
            }

            if (token.isOperator()){
                if (optStack.isEmpty()){
                    optStack.push(token);continue;
                }

                if (token.hasHigherPriority(optStack.peek())){
                    tokens.add(list.get(++i));
                    tokens.add(token);
                } else {
                    tokens.add(optStack.pop());
                    optStack.push(token);
                }
            }
        }

        while (!optStack.isEmpty()){
            tokens.add(optStack.pop());
        }

        return tokens;
	}
	
	

}