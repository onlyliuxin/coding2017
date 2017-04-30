package com.ralf.stack.expr;

import java.util.List;

import com.ralf.stack.MyStack;

public class InfixExpr {

	private String exprString;

	public InfixExpr(String exprString) {
		this.exprString = exprString;
	}

	public double evaluate() {

		MyStack<Float> numStack = new MyStack<Float>();
		MyStack<Token> operStack = new MyStack<>();
		TokenParser parser = new TokenParser();

		List<Token> list = parser.parse(exprString);

		for (Token token : list) {
			if (token.isOperator()) {
				if (operStack.isEmpty()) {
					operStack.push(token);
				} else {
					while (!operStack.isEmpty()
							&& !token.hasHigherPriority(operStack.peek())) {
						String operator = operStack.pop().toString();
						Float num1 = numStack.pop();
						Float num2 = numStack.pop();
						Float result = operate(operator,num1,num2);
						numStack.push(result);
					}
					operStack.push(token);
				}
			}
			if (token.isNumber()) {
				numStack.push(new Float(token.getValue()));
			}
		}
		
		while(!operStack.isEmpty()){
			String operator = operStack.pop().toString();
			Float num1 = numStack.pop();
			Float num2 = numStack.pop();
			Float result = operate(operator,num1,num2);
			numStack.push(result);
		}

		return numStack.pop().floatValue();
	}

	private Float operate(String operator,Float num1, Float num2) {
		float result = 0.0f;
		switch (operator) {
		case "+":
			result = num2 + num1;
			break;
		case "-":
			result = num2 - num1;
			break;
		case "*":
			result = num2 * num1;
			break;
		case "/":
			result = num2 / num1;
			break;
		}
		return result;
	}

}
