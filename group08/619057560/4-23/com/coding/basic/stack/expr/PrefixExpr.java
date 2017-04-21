package com.coding.basic.stack.expr;

import java.util.List;

public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}
	
	private float evaluateWithTokens(List<Token> tokens) throws PrefixExprException {
		if (tokens == null || tokens.isEmpty()) {
			throw new PrefixExprException();
		}
		Token token = tokens.get(0);
		tokens.remove(0);
		if (token.isNumber()) {
			return token.getIntValue();
		}
		if (token.toString().equals("+")) {
			return evaluateWithTokens(tokens) + evaluateWithTokens(tokens);
		} else if (token.toString().equals("-")) {
			return evaluateWithTokens(tokens) - evaluateWithTokens(tokens);
		} else if (token.toString().equals("*")) {
			return evaluateWithTokens(tokens) * evaluateWithTokens(tokens);
		} else if (token.toString().equals("/")) {
			return evaluateWithTokens(tokens) / evaluateWithTokens(tokens);
		} else {
			throw new PrefixExprException();
		}
	}

	public float evaluate() {
		TokenParser parser = new TokenParser();
		try {
			return evaluateWithTokens(parser.parse(expr));
		} catch (PrefixExprException e) {
			e.printStackTrace();
		}
		return 0.0f;
	}
	
	
}
