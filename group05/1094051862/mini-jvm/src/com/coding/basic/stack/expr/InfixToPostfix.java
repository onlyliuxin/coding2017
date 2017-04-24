package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static Token[] convert(String expr) {
		TokenParser parser = new TokenParser();
		List<Token> infix = parser.parse(expr);
		
		Stack<Token> postfix = new Stack<Token>();
		Stack<Token> op = new Stack<Token>();
		for (Token token : infix) {
             if (token.isDigit()) {                  
                 postfix.push(token);
             } /*else if (token.getValue().equals("(")) {
                 op.push(token);
             } else if (token.getValue().equals(")")) {
                 while (!op.isEmpty() && !op.peek().getValue().equals("(")) {
                     postfix.push(op.pop());
                 }
                 op.pop();
             }*/ else if (token.isOperator()){
                 if (op.isEmpty()) {
                     op.push(token);
                 } /*else if (op.peek().getValue().equals("(")) {
                     op.push(token);
                 } */else if (token.hasHigherPriority(op.peek())) {
                     op.push(token);
                 } else {
                	 while (!op.isEmpty()/* &&! op.peek().getValue().equals("(")*/ && !token.hasHigherPriority(op.peek())) {
                		 postfix.push(op.pop());                            
                	 }
                     op.push(token);                            
                 }
             }
         }
		while (!op.isEmpty()) {
			postfix.push(op.pop());
		}
		Token[] result = new Token[postfix.size()];
		return postfix.toArray(result);
	}

}
