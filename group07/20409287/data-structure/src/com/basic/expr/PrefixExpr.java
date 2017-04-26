package com.basic.expr;

import java.util.List;
import java.util.Stack;

public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {

		if (this.expr == null) throw new RuntimeException("运算表达式为空!");

		// 从右至左扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，
		// 用运算符对它们做相应的计算（栈顶元素 op 次顶元素），并将结果入栈；
		// 重复上述过程直到表达式最左端，最后运算得出的值即为表达式的结果。
		TokenParser tokenParser = new TokenParser();
		Stack<Float> stack = new Stack<>();
		List<Token> tokens = tokenParser.parse(this.expr);
		for (int i = tokens.size() - 1; i >= 0; i--) {
			if (tokens.get(i).isNumber()) {
				stack.push((float) tokens.get(i).getIntValue());
				continue;
			}
			Float num1 = stack.pop();
			Float num2 = stack.pop();
			float value;
			switch (tokens.get(i).value) {
				case "+":
					value = num1 + num2;
					break;
				case "-":
					value = num1 - num2;
					break;
				case "*":
					value = num1 * num2;
					break;
				case "/":
					value = (float) num1 / num2;
					break;
				default:
					throw new RuntimeException("不支持的运算符: " + tokens.get(i).value);
			}
			stack.push(value);
		}
		return stack.pop();
	}
	
	
}
