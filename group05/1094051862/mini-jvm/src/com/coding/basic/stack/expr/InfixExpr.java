package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class InfixExpr {
	String expr = null;

	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {

		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);

		Stack<Token> opStack = new Stack();
		Stack<Float> numStack = new Stack();

		for (Token t : tokens) {
			if (t.isOperator()) {
				if (opStack.isEmpty()) {
					opStack.push(t);
				} else {
					/*Token topOperator = (Token) opStack.peek();
					if (t.comparePriority(topOperator) >= 0) {
						opStack.push(t);
					} else {
						Float f1 = (Float) numStack.pop();
						Float f2 = (Float) numStack.pop();
						numStack.push(calculate(topOperator.getValue(), f2, f1));
						opStack.pop();
						opStack.push(t);
					}*/
					while(!opStack.isEmpty() 
							&& !t.hasHigherPriority(opStack.peek())) {
						Token prevOperator = opStack.pop();
						Float f2 = numStack.pop();
						Float f1 = numStack.pop();
						Float result = calculate(prevOperator.toString(), f1, f2);
						
						numStack.push(result);
					}
					opStack.push(t);
					
				}
			}
			if (t.isDigit()) {
				numStack.push(new Float(t.getIntValue()));
			}
		}
		while (!opStack.isEmpty()) {
			Float f1 = numStack.pop();
			Float f2 = numStack.pop();
			Token opr = opStack.pop();
			numStack.push(calculate(opr.getValue(), f2, f1));
		}
		return numStack.pop();
		//return 0.0f;
	}

	private Float calculate(String op, Float f1, Float f2) {
		System.out.println(f1 + "=====" +f2);
		switch (op) {
		case "*":
			System.out.println(" * ");
			return f1 * f2;
		case "/":
			System.out.println("/");
			return f1 / f2;
		case "+":
			System.out.println("+");
			return f1 + f2;
		case "-":
			System.out.println("-");
			return f1 - f2;
		}
		throw new RuntimeException(op + " is not supported");
	}

}
