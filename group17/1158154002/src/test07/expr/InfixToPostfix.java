package test07.expr;

import java.util.ArrayList;
import java.util.List;

import test05.stack.Stack;

public class InfixToPostfix {
	// 2+3*4-8/2 2 3 4 * + 8 2 / -
	// 2+3*4-8/2+3
	// 2+3*4*5*5-8/2 2 3 4 * 5 * 5 * + 8 2 / -
	// 2+3*4*5*5-8/2/2 2 3 4 * 5 * 5 * + 8 2 / -
	public static List<Token> convert(String expr) {

		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		List<Token> result = new ArrayList<Token>();

		Stack<Token> opStack = new Stack<>();
		for (Token token : tokens) {

			if (token.isOperator()) {

				if (opStack.isEmpty()) {
					opStack.push(token);
				} else {

					if (token.hasHigherPriority()&&!opStack.peek().hasHigherPriority()) {
						opStack.push(token);
					} else if (!token.hasHigherPriority()&&opStack.peek().hasHigherPriority()) {
						if (opStack.size() > 1) {
							while (opStack.size() > 0) {
								result.add(opStack.pop());
							}
						}
						opStack.push(token);
					}
					else {
						result.add(token);
					}
				}
			}
			if (token.isNumber()) {
				result.add(token);
			}
		}
		while (opStack.size() > 0) {
			result.add(opStack.pop());
		}
		for (Token token : result) {
			System.out.print(token.value + " ");
		}
		System.out.println();
		return result;
	}
}
