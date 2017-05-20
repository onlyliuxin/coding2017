package com.coding.basic.homework_06.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		List<Token> infixToken = new TokenParser().parse(expr);
		List<Token> posToken = new ArrayList<Token>();
		Stack<Token> stack = new Stack<Token>();
		
		for (Token token : infixToken) {
			if(token.isOperator()){
				while(!stack.empty() && !token.hasHigherPriority(stack.peek())){
					posToken.add(stack.pop());
				}
				stack.push(token);
			}
			if(token.isNumber()){
				posToken.add(token);
			}
		}
		while(!stack.isEmpty()){
			posToken.add(stack.pop());
		}
		
		return posToken;
	}
	

}
