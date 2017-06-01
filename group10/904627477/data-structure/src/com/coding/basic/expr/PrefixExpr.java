package com.coding.basic.expr;

import java.util.List;
import java.util.Stack;

public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}  
	//-++6/*2 9 3 * 4 2 8   4*2 + 6+9*2/3 -8
	public float evaluate() {   
		//TokenParser tp = new TokenParser();
		List<Token> tokens = TokenParser.parse(expr);
		Stack<Token> result = new Stack<Token>();
		for(int i=tokens.size()-1;i>=0;i--){
			Token temp = tokens.get(i);
			if(temp.isNumber()){
				result.push(temp);
			}else if(temp.isOperator()){
				Token temp1 = result.pop();
				Token temp2 = result.pop();
				Token temp3 = TokenParser.calculate(temp, temp1, temp2);
				result.push(temp3);
			}else{
				System.out.println("the token("+temp+") is not number or operator");
			}
		}
		return result.pop().getFloatValue();
	}
	
	
}
