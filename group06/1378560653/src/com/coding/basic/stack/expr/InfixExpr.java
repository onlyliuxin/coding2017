package com.coding.basic.stack.expr;

import java.util.Stack;

/*
 * 中序表达式求值
 * 1.只支持加减乘除，不支持括号
 * 2.表达式中只支持int,结果可能是float
 * 3.用2个栈实现：一个运算符栈，一个操作数栈
 */

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}
	
	public float evaluate() {		
		Stack<Character> opStack = new Stack<>();
		Stack<Float> numStack = new Stack<>();
		
		int i = 0;
		while(i < expr.length()){
			char c = expr.charAt(i);
			if(isOperator(c)){
				if(opStack.isEmpty()){
					opStack.push(c);
				} else { 
					char preOperator = opStack.peek();
					if(ComparePriority(c,preOperator) > 0){
						opStack.push(c);
					} else {
						float num2 = numStack.pop();
						float num1 = numStack.pop();
						numStack.push(calculate(opStack.pop(),num1,num2));
						opStack.push(c);
					}
				}
				i++;
			}
			if(isNumber(c)){
				String str = String.valueOf(c);
				i++;
				while(i < expr.length() && isNumber(expr.charAt(i))){
					str += expr.charAt(i);
					i++;
				}
				numStack.push(Float.parseFloat(str));
			}
		}
		
		while(!opStack.isEmpty()){
			float num2 = numStack.pop();
			float num1 = numStack.pop();
			numStack.push(calculate(opStack.pop(),num1,num2));
		}
		float result = numStack.pop();
		return result;
	}

	private Float calculate(char c, float num1, float num2) {
		if(c == '+'){
			return num1+num2;
		}
		if(c == '-'){
			return num1-num2;
		}
		if(c == '*'){
			return num1*num2;
		}
		if(c == '/'){
			return num1/num2;
		}
		return null;
	}

	private int ComparePriority(char c, char preOperator) {
		if((c == '/' || c == '*')&&(preOperator == '+' || preOperator == '-')){
			return 1;
		}
		return -1;
	}

	private boolean isNumber(char c) {
		if(c >= '0' && c <= '9'){
			return true;
		}
		return false;
	}

	private boolean isOperator(char c) {
		if(c == '+' || c == '-' || c == '*' ||c == '/'){
			return true;
		}
		return false;
	}
}
