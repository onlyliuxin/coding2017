package com.coding.basic.stack.expression;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		
		Stack<Token> opStack = new Stack<>();
		Stack<Token> numStack = new Stack<>();
		
		for (Token token : tokens) {
			if(token.isNumber()){
				numStack.push(token);
			}
			
			if(token.isOperator()){
				if(opStack.isEmpty()){
					opStack.push(token);
				} else {
					Token pre = opStack.peek();
					if(token.hasHigherPriority(pre)){
						opStack.push(token);
					} else {
						while(!opStack.isEmpty()){
							numStack.push(opStack.pop());
						}
						opStack.push(token);
					}
				}
			}
		}
		
		List<Token> temp = new ArrayList<>();
		while(!numStack.isEmpty()){
			temp.add(numStack.pop());
		}
		
		List<Token> result = new ArrayList<>();
		for(int i = temp.size()-1; i >= 0; i--){
			result.add(temp.get(i));
		}
		return result;
	}
}
