package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class PrefixExpr {
	String expr = null;
	List<Token> tokens = null;
	public PrefixExpr(String expr) {
		this.expr = expr;
		TokenParser parser = new TokenParser();
		tokens = parser.parse(expr);
		
	}

	public float evaluate() {
		Stack<Float> cache = new Stack<>();
		int index = tokens.size()-1;
		while(index >= 0){
			if(tokens.get(index).isNumber()){
				cache.push((float) tokens.get(index).getIntValue());
			}
			else{
				float op1 = cache.pop();
				float op2 = cache.pop();
				float r = calculate(op1, op2, tokens.get(index).toString());
				cache.push(r);
			}
			
			index --;
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
