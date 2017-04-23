package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {
String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		
		Stack<Token> opStack = new Stack();
		Stack<Float> numStack = new Stack();
		
		Stack<Token> exprs = new Stack<Token>();
		
		for(int i = tokens.size()-1; i >= 0; i--) {
			exprs.push(tokens.get(i));
		}
		
		while(!exprs.isEmpty()) {
			Token token = exprs.pop();
			if (token.isDigit()) {
				numStack.push(new Float(token.getIntValue()));
			} else {
				Float f1 = numStack.pop();
				Float f2 = numStack.pop();
				numStack.push(calculate(token.toString(), f2, f1));
			}
		}
		
		return numStack.pop().floatValue();
	}
	
	private Float calculate(String op, Float f1, Float f2){
		if(op.equals("+")){
			return f1+f2;
		}
		if(op.equals("-")){
			return f1-f2;
		}
		if(op.equals("*")){
			return f1*f2;
		}
		if(op.equals("/")){
			return f1/f2;
		}
		throw new RuntimeException(op + " is not supported");
	}
	
	
}
