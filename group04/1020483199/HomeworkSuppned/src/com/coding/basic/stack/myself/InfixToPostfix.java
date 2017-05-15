package com.coding.basic.stack.myself;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.coding.basic.stack.myself.Token;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		/*List<Token> infixPost = new TokenParser().parse(expr);

		List<Token> postFixTokens = new ArrayList<>();
		
		Stack<Token> opStack = new Stack<Token>();
		//2-3*4+5   
		for (Token token:infixPost){
			if(token.isOperator()){
				
				while(!opStack.isEmpty() 
						&& !token.hasHigherPriority(opStack.peek())){
					System.out.println("满足条件的token"+token);
					postFixTokens.add(opStack.pop());//	
				}
				
				opStack.push(token);//-
				
			}
			
			if(token.isNumber()){
				
				postFixTokens.add(token);//2 3 
				
			}
		}
		
		while (!opStack.isEmpty()){
			
			postFixTokens.add(opStack.pop());
			
		}
		
		return postFixTokens;*/
		
		List<Token> infixPost = new TokenParser().parse(expr);
		
		List<Token> postFixTokens = new ArrayList<Token>();
		
		Stack<Token> openStack = new Stack<Token>();
		
		for (Token token:infixPost){
			
			if (token.isOperator()){
				
				while (!openStack.isEmpty()
						  && !token.hasHigherPriority(openStack.peek())){
					postFixTokens.add(openStack.pop());
				}
				
				openStack.push(token);
			}
			
			if (token.isNumber()){
				
				postFixTokens.add(token);
			}
		}
		
		while (!openStack.isEmpty()){
			postFixTokens.add(openStack.pop());
		}
		
		return postFixTokens;
	}
	
	

}
