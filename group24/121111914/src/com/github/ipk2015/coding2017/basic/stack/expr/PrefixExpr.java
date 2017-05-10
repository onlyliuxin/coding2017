package com.github.ipk2015.coding2017.basic.stack.expr;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
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
			token = list.get(len-i-1);
			if(token.isNumber()){
				stack.push(Float.valueOf(token.toString()));
			}else{
				preNum = (Float)stack.pop();
				afterNum = (Float)stack.pop();
				stack.push(Token.doBaseOper(preNum, afterNum, token.toString()));
			}
		}
		return (Float)stack.pop();
	}
	
	
}
