package com.coding.basic.expr;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {
String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		List<Token> tokens = TokenParser.parse(expr);
		Stack<Token> result = new Stack<Token>();
		for(int i=0;i<tokens.size();i++){
			Token temp = tokens.get(i);
			if(temp.isNumber()){
				result.push(temp);
			}else if(temp.isOperator()){
				Token temp1 = result.pop();
				Token temp2 = result.pop();
				Token temp3 = TokenParser.calculate(temp, temp2, temp1);
				result.push(temp3);
			}else{
				System.out.println("the token("+temp+") is not number or operator");
			}
		}
		return result.pop().getFloatValue();
	}
	
	
}
