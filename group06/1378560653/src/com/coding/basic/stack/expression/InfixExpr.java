package com.coding.basic.stack.expression;

import java.util.List;
import java.util.Stack;

/*
 * 中序表达式求值
 * 1.开辟2个栈：操作数栈和运算符栈
 * 2.当token为操作数时，直接压入操作数栈
 * 3.当token为运算符时，若运算符栈为空，则直接压入，否则进行运算符优先级比较，优先级高，则直接压入栈，反之计算
 */

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		
		Stack<Token> opStack = new Stack<>();
		Stack<Float> numStack = new Stack<>();
		
		for (Token token : tokens) {
			if(token.isOperator()){
				if(opStack.isEmpty()){
					opStack.push(token);
				} else {
					Token pre = opStack.peek();
					if(token.hasHigherPriority(pre)){
						opStack.push(token);
					} else {
						float f2 = numStack.pop();
						float f1 = numStack.pop();
						numStack.push(calculate(opStack.pop().value,f1,f2));
						opStack.push(token);
					}
				}
			}
			
			if(token.isNumber()){
				numStack.push((float) token.getIntValue());
			}
		}
		
		while(!opStack.isEmpty()){
			Token operator = opStack.pop();
			float f2 = numStack.pop();
			float f1 = numStack.pop();
			numStack.push(calculate(operator.value,f1,f2));
		}

		return numStack.pop();
	}

	private float calculate(String value, float f1, float f2) {
		if(value.equals("+")){
			return f1 + f2;
		}
		
		if(value.equals("-")){
			return f1 - f2;
		}
		
		if(value.equals("*")){
			return f1 * f2;
		}
		
		if(value.equals("/")){
			return f1 / f2;
		}
		
		return -1;
	}
	

}
