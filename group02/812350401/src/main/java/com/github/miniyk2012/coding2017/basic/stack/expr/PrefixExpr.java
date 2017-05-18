package com.github.miniyk2012.coding2017.basic.stack.expr;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 https://en.wikipedia.org/wiki/Polish_notation
 Scan the given prefix expression from right to left
 for each symbol
 {
	 if operand then
		push onto stack
	 if operator then
 {
	 operand1=pop stack
	 operand2=pop stack
	 compute operand1 operator operand2
	 push result onto stack
 }
 }
 return top of stack as result
 */
public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser parser = new TokenParser();
		List<Token> tokens  = parser.parse(expr);
		Collections.reverse(tokens);
		Stack<Token> operandStack = new Stack<>();
		for (Token token: tokens) {
			if (token.isNumber()) {
				operandStack.push(token);
			} else {
				Token operand1 = operandStack.pop();
				Token operand2 = operandStack.pop();
                Token result = ExprUtil.evalute(operand1, token, operand2);
                operandStack.push(result);
			}
		}

		return operandStack.pop().getFloatValue();
	}
	
	
}
