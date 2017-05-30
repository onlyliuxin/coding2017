package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		TokenParser tokenParser=new TokenParser();
		List<Token> tokens=tokenParser.parse(expr);
		List<Token> postfixTokens=new ArrayList<>();
		Stack<Token> opStack=new Stack<>();
		for (Token token : tokens) {
			if (token.isNumber()) {
				postfixTokens.add(token);
			}
			else {
				while (!opStack.isEmpty()&&opStack.peek().hasHigherPriority(token)) {
					postfixTokens.add(opStack.pop());		
				}
				opStack.add(token);
			}
		}
		while (!opStack.isEmpty()) {
			postfixTokens.add(opStack.pop());
			
		}
		return postfixTokens;
	}
	
	public static void main(String[] args) {
		List<Token> post=InfixToPostfix.convert("4*2 + 6+9*2/3 -8");
		for (Token token : post) {
			if (token.isNumber()) {
				System.out.println(token.getIntValue());
			}
			else {
				System.out.println(token.toString());
			}
		}
		
	}

}
