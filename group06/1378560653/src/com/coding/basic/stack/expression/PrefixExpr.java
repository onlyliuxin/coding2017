package com.coding.basic.stack.expression;

import java.util.List;
import java.util.Stack;

/*
 * 前序表达式求值
 * 与后序表达式求值相反，相当于将后序表达式逆置，然后按照后续表达式的规则求值
 * 
 */
public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		
		Stack<Token> opStack = new Stack<>();
		Stack<Float> numStack = new Stack<>();
		
		for(int i = tokens.size()-1; i>=0; i--){
			Token token = tokens.get(i);
			if(token.isOperator()){
				if(opStack.isEmpty()){
					opStack.push(token);
				} else {
					float f1 = numStack.pop();
					float f2 = numStack.pop();
					numStack.push(calculate(opStack.pop().value,f1,f2));
					opStack.push(token);
				}
			}
			
			if(token.isNumber()){
				if(numStack.isEmpty()){
					numStack.push((float) token.getIntValue());
				} else {
					if (opStack.isEmpty()){
						numStack.push((float) token.getIntValue());
					} else {
						float f1 = numStack.pop();
						float f2 = numStack.pop();
						numStack.push(calculate(opStack.pop().value,f1,f2));
						numStack.push((float) token.getIntValue());
					}
				}
			}
		}
		
		while(!opStack.isEmpty()){
			float f1 = numStack.pop();
			float f2 = numStack.pop();
			numStack.push(calculate(opStack.pop().value,f1,f2));
		}
		
		return numStack.pop();
	}
	
	private float calculate(String value, float f1, float f2) {
		if(value.equals("+")){
			return f1 + f2;
		}
		
		if(value.equals("-")){
			return f1 - f2;
		}
		
		if(value.equals("*")){
			return f1 * f2;
		}
		
		if(value.equals("/")){
			return f1 / f2;
		}
		
		return -1;
	}
	
	public static void main(String[] args){
		PrefixExpr expr = new PrefixExpr("-++6/*2 9 3 * 4 2 8");
		System.out.println(expr.evaluate());
	}
}
