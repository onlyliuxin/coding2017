package algorithm.expression;

import datastructure.basic.Stack;

import java.util.EmptyStackException;
import java.util.List;

public class PostfixExpr {

	private String expr;
	private String splitRegex;
	private Stack stack = new Stack();

	public PostfixExpr(String expr) {
		this.expr = expr;
		this.splitRegex = " ";
	}

	public PostfixExpr(String expr, String splitRegex) {
		this.expr = expr;
		this.splitRegex = splitRegex;
	}

	public float evaluate() {
		stack.clear();
		List<Token> tokens = TokenParser.parse(expr, splitRegex);

		try {
			for (Token token : tokens) {
				if (token.isNumber()) {
					stack.push(token);
				} else {
					Token num1 = (Token) stack.pop();
					Token num2 = (Token) stack.pop();
					stack.push(Token.calculate(num2, token, num1));
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
