package com.ralf.stack.expr;

import java.util.List;

import com.ralf.stack.MyStack;

public class PostfixExpr {
	
	private String exprString;
	
	public PostfixExpr(String exprString){
		this.exprString = exprString;
	}

	public float evaluate() {
		MyStack<Float> myStack = new MyStack<>();
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(exprString);
		for(Token token : tokens){
			if (token.isNumber()) {
				myStack.push(new Float(token.getValue()));
			}
			if (token.isOperator()) {
				float f2 = myStack.pop();
				float f1 = myStack.pop();
				myStack.push(calculate(token.toString(), f1, f2));
			}
		}
		return myStack.pop().floatValue();
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

}
