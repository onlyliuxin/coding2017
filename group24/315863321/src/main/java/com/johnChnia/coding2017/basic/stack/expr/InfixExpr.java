package com.johnChnia.coding2017.basic.stack.expr;


import com.johnChnia.coding2017.basic.List;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		List<Token> tokens = InfixToPostfix.convert(this.expr);
		return PostfixExpr.evaluate(tokens);
	}
	
	
}
