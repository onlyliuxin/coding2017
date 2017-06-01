package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {
String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		Stack<Float> stack = new Stack<>();
		char[] tmp = expr.toCharArray();
		int len = tmp.length;
		String num = "";
		for (int i = 0; i <len; i++) {
			char chr=tmp[i];
			if (chr >= '0' && chr <= '9') {
				num += chr;
				if (i==len-1 || tmp[i + 1] < '0' || tmp[i + 1] > '9') {
					float number = Float.parseFloat(num);
					num="";
					stack.push(number);
					continue;
				}
			} else if (chr == '+' || chr == '-' || chr == '*'
					|| chr == '/') {
				float num2 = stack.pop();
				float num1 = stack.pop();
				float answer = eval(chr, num1, num2);
				stack.push(answer);
			}
		}
		return stack.peek();
	}

	public static float eval(char op, float num1, float num2) {
		switch (op) {
		case '+':
			return num1 + num2;
		case '-':
			return num1 - num2;
		case '*':
			return num1 * num2;
		case '/':
			return num1 / num2;
		default:
			throw new IllegalArgumentException(op + " is not supported yet");
		}
	}
	
	
}
