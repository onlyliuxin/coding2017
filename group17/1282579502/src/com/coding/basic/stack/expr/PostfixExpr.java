package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {
String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		List<Token> tokens = TokenParser.parse(expr);
		tokens.forEach(System.out::println);
		Stack<Float> cache = new Stack<>();
		for(Token tk: tokens){
			if(tk.isNumber()){
				cache.push((float)tk.getIntValue());
			}
			else{
				float op2 = cache.pop();
				float op1 = cache.pop();
				
				float r = calculate(op1,op2,tk.toString());
				cache.push(r);
			}
		}
		
		return cache.pop();
	}
	
	private float calculate(float op1, float op2, String operator){
		float result = 0;
		switch(operator){
		case("+"):
			result = op1 + op2;
			break;
		case("-"):
			result = op1 - op2;
			break;
		case("*"):
			result = op1 * op2;
			break;
		case("/"):
			result = op1 / op2;
			break;
		default:
			break;
		}
		return result;
	}
	
	
}
