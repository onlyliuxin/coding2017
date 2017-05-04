package com.coding.basic.expr;

import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	//a+b*c-(d+e)   
	public static List<Token> convert(String expr) {
		List<Token> tokens = TokenParser.parse(expr);
		Stack<Token> exprStack = new Stack<Token>();
		Stack<Token> opStack = new Stack<>();
		for(Token token : tokens){
			if (token.isOperator()){
				if(opStack.isEmpty()){
					opStack.push(token);
				} else{
					while(!opStack.isEmpty()&&!token.hasHigherPriority(opStack.peek())){
						Token prevOperator = opStack.pop();
						Token f1 = exprStack.pop();
						Token f2 = exprStack.pop();
						exprStack.push(new Token(Token.NUMBER, f2+" "+f1+prevOperator));						
					}
					opStack.push(token);
				}
			} 
			if(token.isNumber()){
				exprStack.push(token);
			}
		}
		while(!opStack.isEmpty()){
			Token token = opStack.pop();
			Token f1 = exprStack.pop();
			Token f2 = exprStack.pop();
			exprStack.push(new Token(Token.NUMBER, f2+" "+f1+token));
		}
		List<Token> result = TokenParser.parse(exprStack.pop().toString());
	    return result;
	}
	
	
	
}
