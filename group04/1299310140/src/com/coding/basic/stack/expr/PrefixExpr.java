package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser parser = new TokenParser();
		List<Token> expr_LToken = parser.parse(this.expr);
		
		Stack<Token> expr_SToken = new Stack<Token>();
		for(Token token : expr_LToken){
			expr_SToken.push(token);
		}
		
		Stack<Float> operandStack_SF = new Stack<Float>();
		while(!expr_SToken.isEmpty()){
			Token token = expr_SToken.pop();
			if(token.isNumber()){
				operandStack_SF.push(new Float(token.getIntValue()));
			}else{
				Float f1 = operandStack_SF.pop();
				Float f2 = operandStack_SF.pop();
				Float result = CalculateUtil.calculate(token.toString(), f1, f2);
				operandStack_SF.push(result);
			}
		}
		return operandStack_SF.pop().floatValue();
	}
	
	
}
