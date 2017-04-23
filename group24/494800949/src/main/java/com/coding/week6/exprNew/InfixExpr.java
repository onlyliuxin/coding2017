package com.coding.week6.exprNew;

import com.coding.weak1.Stack;

import java.util.List;

public class InfixExpr {
	String expr = null;

	public InfixExpr(String expr) {
		this.expr = expr;
	}


	public float evaluate() {

		Stack operatorStack = new Stack();
		Stack numberStack = new Stack();
		fillStack(numberStack, operatorStack);
		while (!operatorStack.isEmpty()) {
			Operator symbol = (Operator) operatorStack.pop();
			float operTop1 = (float) numberStack.pop();
			float operTop2 = (float) numberStack.pop();
			numberStack.push(symbol.apply(operTop2, operTop1));
		}
		return (float)numberStack.pop();
	}

	public void fillStack(Stack numberStack, Stack operatorStack) {
		TokenParser tokenParser = new TokenParser();
		List<Token> tokens = tokenParser.parse(expr);
		for (Token token : tokens) {
			if (token.isNumber()) {
				numberStack.push(token.getFloatValue());
			}
			else if (token.isOperator()) {
				Operator o = token.getOperator();
				if (operatorStack.isEmpty()) {
					operatorStack.push(o);
				}else {
					Operator top = (Operator)operatorStack.peek();
					if (o.hasHigherPriority(top)) {
						operatorStack.push(o);
					} else {
						float operTop1 = (float) numberStack.pop();
						float operTop2 = (float) numberStack.pop();
						numberStack.push(top.apply(operTop2, operTop1));
						operatorStack.pop();
						operatorStack.push(o);
					}
				}
			}

		}
	}

}
