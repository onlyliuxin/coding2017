package com.coding.week7;

import com.coding.week6.exprNew.Operator;
import com.coding.week6.exprNew.Token;
import com.coding.week6.exprNew.TokenParser;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PrefixExpr {
	String expr = null;
	final Stack<Float>    numStack;
	final Stack<Operator> operStack;

	public PrefixExpr(String expr) {
		this.expr = expr;
		numStack = new Stack<>();
		operStack = new Stack<>();
	}

	public float evaluate() {
		TokenParser parser = new TokenParser();
		parser.parse(expr);
		List<Token> tokens = parser.parse(expr);
		Collections.reverse(tokens);
		for (Token token : tokens) {
			if (token.isNumber()) {
				numStack.push(token.getFloatValue());
			} else if (token.isOperator()) {
				if (numStack.size() >= 2) {
					Float num1 = numStack.pop();
					Float num2 = numStack.pop();
					Float result = token.getOperator().apply(num1, num2);
					numStack.push(result);
				}
			}
		}
		return numStack.pop();
	}
	
	
}
