package algorithm.expression;

import datastructure.basic.Stack;

import java.util.ArrayList;
import java.util.List;

public class InfixExpr {

	private Stack numbers = new Stack();
	private Stack operators = new Stack();

	private String expr;

	public InfixExpr(String expr) {
		this.expr = expr.replaceAll("\\s", "");
	}

	public float evaluate() {
		numbers.clear();
		operators.clear();
		operators.push(Token.SCOPE);

		List<Token> tokens = TokenParser.parse(expr);
		tokens.add(Token.SCOPE);

		for (int i = 0; i < tokens.size() && !operators.isEmpty(); ++i) {
			Token token = tokens.get(i);
			if (token.isNumber()) {
				putNumber(token);
			} else {
				putOperator(token);
			}
		}
		return numbers.isEmpty() ? 0 : ((Token) numbers.peek()).getFloatValue();
	}

	private void putNumber(Token num) {
		numbers.push(num);
	}

	private void putOperator(Token op) {
		int compare = Token.compare(op, (Token) operators.peek());
		switch (compare) {
			case 1:
				operators.push(op);
				break;
			case 0:
				operators.pop();
				break;
			case -1:
				Token num1 = (Token) numbers.pop();
				Token num2 = (Token) numbers.pop();
				Token operator = (Token) operators.pop();
				Token result = Token.calculate(num2, operator, num1);
				numbers.push(result);
				putOperator(op);
				break;
		}
	}
}
