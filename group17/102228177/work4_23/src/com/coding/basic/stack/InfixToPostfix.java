package com.coding.basic.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**

 * @Description 中缀转后缀表达式 2+3*4  =>  2 3 4 * + 

 * @author REEFE

 * @time 2017年4月26日

 */
public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		TokenParser parser = new TokenParser();
		List<Token> infixTokens = parser.parse(expr);
		List<Token> postfixTokens = new ArrayList<Token>();
		Stack<Token> opStack = new Stack<>();
		for (Token token : infixTokens) {
			if(token.isOperator()){
				while(!opStack.isEmpty() && 
						!token.hasHigherPriority(opStack.peek())){
					postfixTokens.add(opStack.pop());
				}
				opStack.push(token);
			}
			if(token.isNumber()){
				postfixTokens.add(token);
			}
		}
		while(!opStack.isEmpty()){
			postfixTokens.add(opStack.pop());	
		}
		return postfixTokens;
	}
}
