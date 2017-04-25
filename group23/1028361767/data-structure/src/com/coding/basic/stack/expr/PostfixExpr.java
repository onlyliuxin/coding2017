package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {
String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		Stack<Float> numStack = new Stack<>();
		
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr); 
		for(Token token : tokens) {
			if(token.isNumber()){
				numStack.push(new Float(token.getIntValue()));
			}else{
				String op = token.value;
				float num2 = numStack.pop();
				float num1 = numStack.pop();
				float result = calculate(op, num1, num2);
				numStack.push(result);
			}
		}
		return numStack.pop();
	}
	
	private Float calculate(String op, Float f1, Float f2){
		if(op.equals("+")){
			return f1+f2;
		}
		if(op.equals("-")){
			return f1-f2;
		}
		if(op.equals("*")){
			return f1*f2;
		}
		if(op.equals("/")){
			return f1/f2;
		}
		throw new RuntimeException(op + " is not supported");
	}
	
	private void reverse(Stack s){
		Stack tmp = new Stack();
		int len = s.size();
		for(int i=0;i<len;i++){
			Object top = s.pop();
			while(s.size() > i) {
				tmp.push(s.pop());
			}
			s.push(top);
			while(tmp.size() > 0){
				s.push(tmp.pop());
			}
		}
	}
}
