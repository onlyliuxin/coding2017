package com.donaldy.basic.expr;

import java.util.List;
import java.util.Stack;

/**
 *	从右到左遍历
 *  1. 若为数字，则入栈
 *  2. 若为符号，则calculate
 */
public class PrefixExpr {

	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {


		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(this.expr);

		Stack<Float> numStack = new Stack<>();
		Stack<String> opStack = new Stack<>();

		for(int i = tokens.size() - 1; i >= 0  ; --i){
			Token token = tokens.get(i);

			if (token.isOperator()){
				opStack.push(token.toString());
				Float f1 = numStack.pop();
				Float f2 = numStack.pop();
				numStack.push(calculate(opStack.pop(), f1,f2));
			}

			if(token.isNumber()){
				numStack.push(new Float(token.getIntValue()));
			}
		}

		while (!opStack.isEmpty()) {
			Float f1 = numStack.pop();
			Float f2 = numStack.pop();
			numStack.push(calculate(opStack.pop(), f1,f2));
		}

		return numStack.pop().floatValue();
	}

	private Float calculate(String op, Float f1, Float f2){
		if(op.equals("+")){
			return f1+f2;
		}
		if(op.equals("-")){
			return f1-f2;
		}
		if(op.equals("*")){
			return f1*f2;
		}
		if(op.equals("/")){
			return f1/f2;
		}
		throw new RuntimeException(op + " is not supported");
	}
	
	
}
