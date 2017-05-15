package com.coding.basic.stack.expr;

import java.util.List;

public class PostfixExpr {
	String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}
	
	public static float evaluateWithTokens(List<Token> tokens) throws PostfixExprException {
		if (tokens == null || tokens.isEmpty()) {
			throw new PostfixExprException();
		}
		Token token = tokens.get(tokens.size() - 1);
		tokens.remove(token);
		if (token.isNumber()) {
			return token.getIntValue();
		}
		if (token.toString().equals("+")) {
			return evaluateWithTokens(tokens) + evaluateWithTokens(tokens);
		} else if (token.toString().equals("-")) {
			return 0 - evaluateWithTokens(tokens) + evaluateWithTokens(tokens);
		} else if (token.toString().equals("*")) {
			return evaluateWithTokens(tokens) * evaluateWithTokens(tokens);
		} else if (token.toString().equals("/")) {
			return 1 / evaluateWithTokens(tokens) * evaluateWithTokens(tokens);
		} else {
			throw new PostfixExprException();
		}
	}
	
	public float evaluate() {
		TokenParser parser = new TokenParser();
		
		try {
			return evaluateWithTokens(parser.parse(expr));
		} catch (PostfixExprException e) {
			e.printStackTrace();
		}
		return 0.0f;
	}
	
	
}
