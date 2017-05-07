package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;



public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		TokenParser parser=new TokenParser();
		List<Token> exprs=parser.parse(expr);
		List<Token>  numStack=new ArrayList<Token>();
		Stack<Token> ope=new Stack<Token>();
		for(Token token:exprs){
			if(token.isOperator()){
				if(!ope.isEmpty()&&!token.hasHigherPriority(ope.peek())){
					numStack.add(ope.pop());
				}
				ope.push(token);
			}
			else if(token.isNumber()){
				numStack.add(token);
			}
		}
		while(!ope.isEmpty()){
			numStack.add(ope.pop());
		}
		return numStack;
	}
	
	

}
