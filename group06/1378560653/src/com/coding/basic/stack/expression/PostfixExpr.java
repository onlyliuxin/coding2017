package com.coding.basic.stack.expression;

import java.util.List;
import java.util.Stack;

/*
 * 后序表达式， 本身就是表达式计算的顺序
 * 后序表达式求值：
 * 1.开辟2个栈：操作数栈和运算符栈
 * 2.当token为操作数时，检查操作数栈，若操作数栈为空，则压入栈中；否则，判断运算符栈是否为空，若不为空则压入栈中，否则计算
 * 3.当token为运算符时，检查运算符栈，若运算符栈为空，则压入栈中；否则，计算。
 */
public class PostfixExpr {
	String expr = null;
	
	public PostfixExpr(String expr) {
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
					float f2 = numStack.pop();
					float f1 = numStack.pop();
					numStack.push(calculate(opStack.pop().value,f1,f2));
					opStack.push(token);
				}
			}
			
			if(token.isNumber()){
				if(numStack.isEmpty()){
					numStack.push((float) token.getIntValue());
				} else {
					if (opStack.isEmpty()){
						numStack.push((float) token.getIntValue());
					} else {
						float f2 = numStack.pop();
						float f1 = numStack.pop();
						numStack.push(calculate(opStack.pop().value,f1,f2));
						numStack.push((float) token.getIntValue());
					}
				}
			}
		}
		while(!opStack.isEmpty()){
			float f2 = numStack.pop();
			float f1 = numStack.pop();
			numStack.push(calculate(opStack.pop().value,f1,f2));
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
	
	public static void main(String[] args){
		PostfixExpr expr = new PostfixExpr("6 5 2 3 + 8 * + 3 + *");
		System.out.println(expr.evaluate());
	}
}
