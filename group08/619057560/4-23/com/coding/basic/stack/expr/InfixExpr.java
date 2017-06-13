package com.coding.basic.stack.expr;

import java.util.List;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		try {
			List<Token> postfixTokens = InfixToPostfix.convert(expr);
			return PostfixExpr.evaluateWithTokens(postfixTokens);
		} catch (PostfixExprException e) {
			e.printStackTrace();
		} catch (InfixExprException e) {
			e.printStackTrace();
		}
		return 0.0f;
	}
	
	
}
