package algorithm.expression;

import datastructure.basic.Stack;

import java.util.ArrayList;
import java.util.List;

public class InfixToPostfix {
	public static List<Token> convert(String expr) {
		List<Token> infix = TokenParser.parse(expr);
		List<Token> postfix = new ArrayList<>();
		Stack op = new Stack();

		for (Token token : infix) {
			if (token.isNumber()) {
				postfix.add(token);
			} else if (token.equals(Token.R_BRACKET)) {
				while (!op.peek().equals(Token.L_BRACKET)) {
					postfix.add((Token) op.pop());
				}
				op.pop();
			} else {
				while (!op.isEmpty() && Token.compare(token, (Token) op.peek()) < 0) {
					postfix.add((Token) op.pop());
				}
				op.push(token);
			}
		}
		while (!op.isEmpty()) {
			postfix.add((Token) op.pop());
		}
		return postfix;
	}
}
