package algorithm.expression;

import datastructure.basic.Stack;

import java.util.EmptyStackException;
import java.util.List;

public class PrefixExpr {
	private String expr;
	private String splitRegex;
	private Stack stack = new Stack();

	public PrefixExpr(String expr) {
		this.expr = expr;
		this.splitRegex = " ";
	}

	public PrefixExpr(String expr, String splitRegex) {
		this.expr = expr;
		this.splitRegex = splitRegex;
	}

	public float evaluate() {
		stack.clear();
		List<Token> tokens = TokenParser.parse(expr, splitRegex);

		try {
			for (int i = tokens.size() - 1; i >= 0; --i) {
				Token token = tokens.get(i);
				if (token.isNumber()) {
					stack.push(token);
				} else {
					Token num1 = (Token) stack.pop();
					Token num2 = (Token) stack.pop();
					Token result = Token.calculate(num1, token, num2);
					stack.push(result);
				}
			}
		} catch (EmptyStackException e) {
			throw new RuntimeException("Wrong expression: " + expr);
		}
		if (stack.size() != 1) {
			throw new RuntimeException("Wrong expression: " + expr);
		}
		return ((Token) stack.pop()).getFloatValue();
	}
}
