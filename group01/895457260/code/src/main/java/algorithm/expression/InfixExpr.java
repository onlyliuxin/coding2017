package algorithm.expression;

import datastructure.basic.Stack;

import java.util.ArrayList;
import java.util.List;

public class InfixExpr {

	private Stack numbers = new Stack();
	private Stack operators = new Stack();

	private String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
		operators.push("#");
	}

	public float evaluate() {
		numbers.clear();
		operators.clear();
		operators.push("#");

		List<Token> tokens = TokenParser.parse(expr);
		tokens.add(new Token(Token.OPERATOR, "#"));

		for (int i = 0; !operators.isEmpty() && i < tokens.size(); ++i) {
			Token token = tokens.get(i);
			if (token.isNumber()) {
				putNumber(token.getFloatValue());
			} else {
				putOperator(token.toString());
			}
		}
		return numbers.isEmpty() ? 0 : (float) numbers.peek();
	}

	private void putNumber(float num) {
		numbers.push(num);
	}

	private void putOperator(String op) {
		int compare = Token.compare(op, (String) operators.peek());
		switch (compare) {
			case 1:
				operators.push(op);
				break;
			case 0:
				operators.pop();
				break;
			case -1:
				float num1 = (float) numbers.pop();
				float num2 = (float) numbers.pop();
				String operator = (String) operators.pop();
				float result = calculate(num2, operator, num1);
				numbers.push(result);
				putOperator(op);
				break;
		}
	}

	private float calculate(float num2, String op, float num1) {
		switch (op) {
			case "+":
				return num2 + num1;
			case "-":
				return num2 - num1;
			case "*":
				return num2 * num1;
			case "/":
				if (num1 != 0) {
					return num2 / num1;
				}
		}
		throw new RuntimeException("Divide by 0");
	}
}
