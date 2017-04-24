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
		List<Token> tokens = parser.parse(expr);
		
		Stack<Token> opStack = new Stack();
		Stack<Float> numStack = new Stack();
		
		Stack<Token> exprs = new Stack<Token>();
		for(Token token : tokens) {
			exprs.push(token);
		}
		
		while(!exprs.isEmpty()) {
			Token token = exprs.pop();
			if (token.isDigit()) {
				numStack.push(new Float(token.getIntValue()));
			} else {
				Float f1 = numStack.pop();
				Float f2 = numStack.pop();
				numStack.push(calculate(token.toString(), f1, f2));
			}
		}
		
		return numStack.pop().floatValue();
	}

	private Float calculate(String op, Float f1, Float f2) {
		System.out.println(f1 + "=====" +f2);
		switch (op) {
		case "*":
			System.out.println(" * ");
			return f1 * f2;
		case "/":
			System.out.println("/");
			return f1 / f2;
		case "+":
			System.out.println("+");
			return f1 + f2;
		case "-":
			System.out.println("-");
			return f1 - f2;
		}
		throw new RuntimeException(op + " is not supported");
	}
	
	
}
