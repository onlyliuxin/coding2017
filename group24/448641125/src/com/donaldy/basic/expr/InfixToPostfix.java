package com.donaldy.basic.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {

		Stack<Token> opStack = new Stack<>();

		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);

		List<Token> newTokens = new ArrayList<>();

		for (Token token : tokens) {

			if (token.isOperator()) {

				while (true) {
					if (opStack.isEmpty()) {
						opStack.push(token);

						break;
					}

					if (token.hasHigherPriority(opStack.peek())) {

						opStack.push(token);

						break;
					}

					newTokens.add(opStack.pop());

				}


			}

			if (token.isNumber()) {
				newTokens.add(token);
			}

		}

		while (!opStack.isEmpty()) {
			newTokens.add(opStack.pop());
		}

		
		return newTokens;
	}
	
	

}
