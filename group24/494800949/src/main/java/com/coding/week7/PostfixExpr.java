package com.coding.week7;

import com.coding.week6.exprNew.Operator;
import com.coding.week6.exprNew.Token;
import com.coding.week6.exprNew.TokenParser;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {
	String expr = null;
	final Stack<Float> numStack;
	final Stack<Operator> operStack;

	public PostfixExpr(String expr) {
		this.expr = expr;
		this.numStack = new Stack<>();
		this.operStack = new Stack<>();
	}

	public float evaluate() {
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		for (Token token : tokens) {
			if (token.isNumber()) {
				numStack.push(token.getFloatValue());
			} else if (token.isOperator()) {
				if (numStack.size() >= 2) {
					Float num1 = numStack.pop();
					Float num2 = numStack.pop();
					Float result = token.getOperator().apply(num2, num1);
					numStack.push(result);
				}
			}
		}
		return numStack.pop();
	}
	
}
