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
		List<Token> expr_LToken = parser.parse(this.expr);
		
		Stack<Float> operandStack_SF = new Stack<Float>();
		for(Token token : expr_LToken){
			if(token.isNumber()){
				operandStack_SF.push(new Float(token.getIntValue()));
			}else{
				Float f2 = operandStack_SF.pop();
				Float f1 = operandStack_SF.pop();
				Float result = CalculateUtil.calculate(token.toString(), f1, f2);
				operandStack_SF.push(result);
			}
		}
		return operandStack_SF.pop().floatValue();
	}
	
	
}
