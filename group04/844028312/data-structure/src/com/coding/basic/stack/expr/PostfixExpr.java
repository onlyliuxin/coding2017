package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {
String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser tokenParser=new TokenParser();
		List<Token> tokens=tokenParser.parse(expr);
		Stack stack=new Stack();
		for(Token token:tokens){
			if(token.isNumber()){
				stack.push(new Float(token.getIntValue()));
			}
			else{
				float one =(float) stack.pop();
				float two=(float) stack.pop();
				float result = 0;
				String ope=token.toString();
				if(ope.equals("-")){
					result=two-one;
				}
				else if(ope.equals("*")){
					result=two*one;
				}
				else if(ope.equals("/")){
					result=two/one;
				}
				else if(ope.equals("+")){
					result=two+one;
				}
				stack.push(result);
			}
		}
		return (float) stack.pop();
	}
	
	
}
