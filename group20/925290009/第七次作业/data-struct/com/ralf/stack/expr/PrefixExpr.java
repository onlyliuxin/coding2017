package com.ralf.stack.expr;

import java.util.List;

import com.ralf.stack.MyStack;

public class PrefixExpr {

	private String exprString;
	
	public PrefixExpr(String exprString) {
		this.exprString = exprString;
	}

	public float evaluate() {
		
		MyStack<Float> myStack = new MyStack<>();
		MyStack<Token> exprStack = new MyStack<>();
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(exprString);
		
		for(Token token : tokens){
			exprStack.push(token);
		}
		
		while(!exprStack.isEmpty()){
			Token token = exprStack.pop();
			if (token.isNumber()) {
				myStack.push(new Float(token.getValue()));
			}
			else {
				Float f1 = myStack.pop();
				Float f2 = myStack.pop();
				myStack.push(calculate(token.toString(),f1,f2));
			}
		}
		return myStack.pop().floatValue();
	}

	private Float calculate(String operator, Float f1, Float f2) {
		if ("+".equals(operator)) {
			return f1 + f2;
		}
		if ("-".equals(operator)) {
			return f1 - f2;
		}
		if ("*".equals(operator)) {
			return f1 * f2;
		}
		if ("/".equals(operator)) {
			return f1 / f2;
		}
		else {
			throw new RuntimeException("this operator is not supported!");
		}
		
	}

}
