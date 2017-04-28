package com.ralf.stack.expr;

import java.util.ArrayList;
import java.util.List;

import com.ralf.stack.MyStack;
import com.ralf.stack.StackUtil;

public class InfixToPostExpr {

	public static List<Token> convert(String infixString){
		
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(infixString);
		
		MyStack<Token> S1 = new MyStack<Token>();
		MyStack<Token> S2 = new MyStack<>();
		
		
		for(Token token : tokens){
			if (token.isNumber()) {
				S2.push(token);
			}
			else{
				while(!S1.isEmpty() && !token.hasHigherPriority(S1.peek())) {
					S2.push(S1.pop());
				}
				S1.push(token);
			}
		}
		while(!S1.isEmpty()){
			S2.push(S1.pop());
		}
		ArrayList<Token> list = new ArrayList<>();
		StackUtil.reverse(S2);
		while(!S2.isEmpty()){
			list.add(S2.pop());
		}
		return list;
	}
}
