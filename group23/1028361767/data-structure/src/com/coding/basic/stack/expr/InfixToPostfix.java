package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		// 3*20+12*5-40/2 --> 3 20 * 12 5 * + 40 2 / -
		// 10-30+50       --> 10 30 - 50 +
		List<Token> ret = new ArrayList<>();
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		Stack<Token> opStack = new Stack<>();
		for(Token token : tokens){
			if(token.isNumber()){
				ret.add(token);
			}else{
				while(!opStack.isEmpty() && opStack.peek().hasHigherPriority(token)){
					ret.add(opStack.pop());
				}
				opStack.push(token);
			}
		}
		while(!opStack.isEmpty()){
			ret.add(opStack.pop());
		}
		return ret;
	}
	
	public static String toString(List<Token> tokens){
		StringBuilder sb = new StringBuilder();
		for(Token token : tokens){
			sb.append(token.value);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(toString(convert("10-30+50")));
		System.out.println(toString(convert("3*20+12*5-40/2")));
	}
}
