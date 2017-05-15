package com.coding.basic.homework_06.expr;

import java.util.List;
import java.util.Stack;


public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		Stack<Float> numStack = new Stack<Float>();
		
		for(int i=tokens.size()-1; i>=0; i--){
			if(tokens.get(i).isOperator()){
				float f1 = numStack.pop();
				float f2 = numStack.pop();
				numStack.push(getResult(f2, f1, tokens.get(i).value));
			}else
			if(tokens.get(i).isNumber()){
				numStack.push(Float.valueOf(tokens.get(i).getIntValue()));
			}
		}
		return numStack.pop();
	}
	
	private float getResult(float float1, float float2, String operator){
		if(operator.equals("+")){
			return float1 + float2;
		}else if(operator.equals("-")){
			return float2 - float1;
		}else if(operator.equals("*")){
			return float1 * float2;
		}else if(operator.equals("/")){
			return float2 / float1;
		}else{
			throw new RuntimeException(operator + " is not supported");
		}
	}
}
