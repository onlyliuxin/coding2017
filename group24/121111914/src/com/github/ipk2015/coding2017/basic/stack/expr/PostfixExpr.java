package com.github.ipk2015.coding2017.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {
String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser parser = new TokenParser();
		List<Token> list = parser.parse(expr);
		
		Stack stack = new Stack();
		int len = list.size();
		float preNum,afterNum;
		Token token;
		for(int i = 0;i<len;i++){
			token = list.get(i);
			if(token.isNumber()){
				stack.push(Float.valueOf(token.toString()));
			}else{
				afterNum = (Float)stack.pop();
				preNum = (Float)stack.pop();
				stack.push(Token.doBaseOper(preNum, afterNum, token.toString()));
			}
		}
		return (Float)stack.pop();
	}
	
	
}
