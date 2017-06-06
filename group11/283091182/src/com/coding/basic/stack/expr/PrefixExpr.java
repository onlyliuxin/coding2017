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
		List tokens = parser.parse(expr);
		Stack op = new Stack();
		Stack num = new Stack();
		while(!tokens.isEmpty()){
			Token t = getFromRight(tokens);
			if(t.isNumber()){
				num.push(t.toString());
			}
			if(t.isOperator()){
				//sequence has minor difference with postfixExpr
				float num1 = Float.parseFloat((String)num.pop());
				float num2 = Float.parseFloat((String)num.pop());
				String result = String.valueOf(calculate(num1,t.toString(),num2));
				System.out.println("Calulating:"+num1+" "+t+" "+num2+" = "+result);
				num.push(result);
			}
		}
		return Float.parseFloat((String)num.pop());
	}

	private Token getFromRight(List tokens) {
		// TODO Auto-generated method stub
		return (Token)tokens.remove(tokens.size()-1);
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
