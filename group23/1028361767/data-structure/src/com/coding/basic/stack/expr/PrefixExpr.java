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
		List<Token> tokens = parser.parse(this.expr);
		Stack<Token> opStack = new Stack<>();
		Stack<Float> numStack = new Stack<>();
		int preContinueOp = 0;
		int preContinueNum = 0;
		for(Token token : tokens){
			if(token.isOperator()) {
				opStack.push(token);
				if(preContinueNum > 0){
					preContinueNum = 0;
					preContinueOp = 1;
				}else{
					preContinueOp++;
				}
			}else{
				numStack.push(new Float(token.getIntValue()));
				preContinueNum++;
			}
			if(preContinueNum >= 2 && preContinueOp > 0){
				float num2 = numStack.pop();
				float num1 = numStack.pop();
				float result = calculate(opStack.pop().value, num1, num2);
				numStack.push(result);
				preContinueNum--;
				preContinueOp--;
			}
		}
		if(!opStack.isEmpty()){
			reverse(numStack);
		}
		while(!opStack.isEmpty()) {
			String op = opStack.pop().value;
			float num1 = numStack.pop();
			float num2 = numStack.pop();
			float result = calculate(op, num1, num2);
			numStack.push(result);
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
