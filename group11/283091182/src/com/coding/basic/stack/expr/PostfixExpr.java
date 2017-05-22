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
		List tokens = parser.parse(expr);
		Stack op = new Stack();
		Stack num = new Stack();
		while(!tokens.isEmpty()){
			Token t = getFromLeft(tokens);
			if(t.isNumber()){
				num.push(t.toString());
			}
			if(t.isOperator()){
				float num2 = Float.parseFloat((String)num.pop());
				float num1 = Float.parseFloat((String)num.pop());
				String result = String.valueOf(calculate(num1,t.toString(),num2));
				System.out.println("Calulating:"+num1+" "+t+" "+num2+" = "+result);
				num.push(result);
			}
		}
		return Float.parseFloat((String)num.pop());
	}
	
	private Token getFromLeft(List tokens){
		return (Token)tokens.remove(0);
	}
	
	private float calculate(float num1,String operator, float num2){
		if("+".equals(operator)){
			return num1+num2;
		}else if("-".equals(operator)){
			return num1-num2;
		}else if("*".equals(operator)){
			return num1*num2;
		}else if("/".equals(operator)){
			return num1/num2;
		}else{
			throw new RuntimeException("Unsupported operator:"+operator);
		}
	}
	
}
