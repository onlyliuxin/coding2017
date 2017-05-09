package com.coding.basic.stack.expr;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		Stack<String> stack = new Stack<String>();
		TokenParser parser = new TokenParser();
		List<Token> tokens  = parser.parse(expr);
		Collections.reverse(tokens);
		for(Token token :tokens){
			if(token.isNumber()){
				stack.push(token.toString());
			}
			if(token.isOperator()){
				int num1 = Integer.parseInt(stack.pop());
				int num2 = Integer.parseInt(stack.pop());
				String result = parseToResult(num1,num2,token.toString());
				stack.push(result);
			}
		}
		
		return Float.parseFloat(stack.pop());
	}
	
	
	private String parseToResult(int num1, int num2, String oper) {
		// TODO Auto-generated method stub
		String result = "";
		if(oper.equals("*"))
			result = (num1*num2)+"";
		if(oper.equals("/"))
			result = (num1/num2)+"";
		if(oper.equals("+"))
			result = (num1+num2)+"";
		if(oper.equals("-"))
			result = (num1-num2)+"";
		return result;
	}
	
}