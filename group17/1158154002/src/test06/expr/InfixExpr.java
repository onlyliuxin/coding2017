package test06.expr;

import java.util.Arrays;

import test05.stack.Stack;
import test05.stack.StackUtil;

public class InfixExpr {
	String expr = null;

	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		String[] str = expr.split("\\+|\\-|\\*|\\/");
		System.out.println(Arrays.toString(str));

		Stack<Character> symbol = new Stack<>();
		Stack<Float> num = new Stack<>();

		for (int i = 0; i < str.length; i++) {
			num.push(Float.valueOf(str[i]));
		}
		
		for (int i = 0; i < expr.length(); i++) {
			char c = expr.charAt(i);
			if (c == '+' || c == '-' || c == '*' || c == '/') {
				symbol.push(c);
			}
		}
		// 逆置
		StackUtil.reverse(symbol);
		StackUtil.reverse(num);

		// 取数，符号计算
		float result = 0;
		while (num.size() > 1) {
			float a = num.pop();
			float b = num.pop();

			char s1 = symbol.pop();
			char s2 = ' ';
			if (symbol.size() > 0) {
				s2 = symbol.pop();
				if (s1 == '*') {
					result = a * b;
					symbol.push(s2);
				} else if (s1 == '/') {
					result = a / b;
					symbol.push(s2);
				} else if (s2 != ' ') {
					if (s2 == '*' || s2 == '/') {
						if (s2 == '*') {
							result = b * num.pop();
						} else if (s2 == '/') {
							result = b / num.pop();
						}
						if (s1 == '+') {
							result = a + result;
						} else if (s1 == '-') {
							result = a - result;
						}
					} else {
						if (s1 == '+') {
							result = a + b;
							symbol.push(s2);
						} else if (s1 == '-') {
							result = a - b;
							symbol.push(s2);
						}
					}
				}
				num.push(result);
				System.out.println(result);
			} else {
				if (s1 == '+') {
					result = a + b;
				} else if (s1 == '-') {
					result = a - b;
				} else if (s1 == '*') {
					result = a * b;
				} else if (s1 == '/') {
					result = a / b;
				}
				System.out.println(result);
			}
		}

		return result;
	}
}
