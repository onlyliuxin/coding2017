package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class InfixExpr {

	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {

		TokenParser tokenParser = new TokenParser();
		List<Token> tokens = tokenParser.parse(expr);

		if (!check(tokens)){
			throw new RuntimeException("illegal expression");
		}

		Stack<Token> operatorStack = new Stack<Token>();
		Stack<Token> numberStack = new Stack<Token>();

		for (Token token : tokens){

			if (token.isNumber()){
				numberStack.push(token);
			}

			if (token.isOperator()){

				if (operatorStack.isEmpty() || token.hasHigherPriority(operatorStack.peek())){
					operatorStack.push(token);

				} else {
					popOneOperator(operatorStack, numberStack);

					if (operatorStack.isEmpty() || token.hasHigherPriority(operatorStack.peek())){
						operatorStack.push(token);

					} else {
						popOneOperator(operatorStack, numberStack);
						operatorStack.push(token);
					}
				}
			}
		}

		while (!operatorStack.isEmpty()){

			popOneOperator(operatorStack, numberStack);
		}

		return Float.valueOf(numberStack.pop().toString());
	}

	private void popOneOperator(Stack<Token> operatorStack, Stack<Token> numberStack){

		Token operator = operatorStack.pop();
		Token num1 = numberStack.pop();
		Token num2 = numberStack.pop();
		float res = evaluateTwo(num2, num1, operator);
		numberStack.push(new Token(Token.NUMBER, String.valueOf(res)));
	}

	private float evaluateTwo(Token num1, Token num2, Token o){

		Float s1 = Float.valueOf(num1.toString());
		Float s2 = Float.valueOf(num2.toString());

		if (o.toString().equals("+")){
			return s1 + s2;

		} else if (o.toString().equals("-")){
			return s1 - s2;

		} else if (o.toString().equals("*")){
			return s1 * s2;

		} else if (o.toString().equals("/")){
			return s1 / s2;
		}

		throw new RuntimeException("unsupported operator [" + o.toString() + "]");
	}

	private boolean check(List<Token> tokens) {

		if (tokens == null || tokens.isEmpty()){
			return false;
		}

		int i = 0;
		for (Token token : tokens){
			if (token.isNumber()){
				i++;
				if ( i != 1){
					return false;
				}
			}

			if (token.isOperator()){
				i--;
				if ( i != 0){
					return false;
				}
			}
		}
		return i == 1;
	}
	
	
}
