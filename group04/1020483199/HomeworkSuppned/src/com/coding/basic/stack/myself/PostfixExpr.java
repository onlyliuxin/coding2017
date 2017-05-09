package com.coding.basic.stack.myself;

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
		
		Stack<Float> numStack = new Stack<Float>();
		for (Token token:tokens){
			if (token.isNumber()){
				numStack.push(new Float(token.getIntValue()));
			}else{
				float f2 = numStack.pop();
				float f1 = numStack.pop();
				float result = calculate(token.toString(), f1, f2);
				numStack.push(result);
			}
			
			
		}
		return numStack.pop().floatValue();
	}
	
	public float calculate(String op, float f1, float f2){
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
