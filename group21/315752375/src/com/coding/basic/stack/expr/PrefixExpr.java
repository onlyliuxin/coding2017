package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class PrefixExpr {
	String expr = null;

	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		Stack<Float> stack = new Stack<>();
		char[] tmp = expr.toCharArray();
		int len = tmp.length;
		String num = "";
		for (int i = len - 1; i > -1; i--) {
			char chr=tmp[i];
			if (chr >= '0' && chr <= '9') {
				num = chr + num;
				if (i==0 || tmp[i - 1] < '0' || tmp[i - 1] > '9') {
					float number = Float.parseFloat(num);
					num="";
					stack.push(number);
					continue;
				}
			} else if (chr == '+' || chr == '-' || chr == '*'
					|| chr == '/') {
				float num1 = stack.pop();
				float num2 = stack.pop();
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
