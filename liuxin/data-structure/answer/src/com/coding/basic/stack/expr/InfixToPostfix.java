package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		
		Stack<Token> s = new Stack<Token>();
		List<Token> result = new ArrayList<Token>();
		
		for(Token token : tokens){
			if(token.isOperator()){
				if(token.toString().equals("*") || token.toString().equals("/")){
					
					while (!s.empty() 
							&& !s.peek().toString().equals("+") 
							&& !s.peek().toString().equals("-")) {
						
						result.add(s.pop());
						
					}
					
					s.push(token);
				}
				if(token.toString().equals("+") || token.toString().equals("-")){
					while (!s.empty() ) {
						result.add(s.pop());
					}
					s.push(token);
				}
			}
			if(token.isNumber()){
				result.add(token);
			}
		}
		
		return result;
	}
	
	public static void main(String[] args){
		List<Token> tokens = convert("10+20*2");
		System.out.println(tokens);
		
	}

}
