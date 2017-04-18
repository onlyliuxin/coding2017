package com.donaldy.basic.expr;

import com.donaldy.basic.Stack;

/**
 * 针对最后一个用例，expr: 10 - 30 + 50;
 * 负数，直接对后面的数进行取反（实际上计算机就是这样做的，组原有提。）
 * 即：expr: 10 - 30 + 50
 * 	  处理后: 10 + -30 + 50
 */
public class InfixExpr {

	String expr = null;

	Stack numStack = new Stack();
	Stack symbolStack = new Stack();
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {		

		if (!this.numStack.isEmpty())
			return (float) this.numStack.peek();

		char [] arr = this.expr.toCharArray();

		parseCharArray(arr);

		remainOperate();

		return (float) this.numStack.peek();
	}

	private void parseCharArray(char [] arr) {

		for (int i = 0; i < arr.length; ) {

			i = parseNumberReturnIndex(i, arr);

			if (i >= arr.length)
				break;

			i = parseSymbolReturnIndex(i, arr);
		}
	}

	private int parseNumberReturnIndex(int index, char [] arr) {
		if (arr[index] <= '9' && arr[index] >= '0' ) {

			float value = arr[index ++] - '0';

			while (index < arr.length && arr[index] <= '9' && arr[index] >= '0' ) {
				value *= 10;
				value += arr[index] - '0';
				index ++;
			}
			this.numStack.push(value);
		}

		return index;
	}

	private int parseSymbolReturnIndex(int index, char[] arr) {

		if ("+-*/".contains(arr[index] + "")) {

			char operator = arr[index ++];

			if (operator == '+') {
				this.symbolStack.push('+');
			}

			if (operator == '-') {

				this.symbolStack.push('+');

				float value = arr[index ++] - '0';

				while (index < arr.length && arr[index] <= '9' && arr[index] >= '0') {
					value *= 10;
					value += arr[index] - '0';
					index ++;
				}

				this.numStack.push(-value);
			}

			if (operator == '*' || operator == '/') {

				float value1 = (float) this.numStack.pop();
				float value2 = arr[index ++] - '0';

				while (index < arr.length && arr[index] <= '9' && arr[index] >= '0') {
					value2 *= 10;
					value2 += arr[index] - '0';
					index ++;
				}

				this.numStack.push(operate(value2, value1, operator));

			}

		}

		return index;
	}

	private void remainOperate() {
		while (!this.symbolStack.isEmpty()) {
			if (this.numStack.size() < 2 || this.symbolStack.size() < 1)
				throw new IndexOutOfBoundsException("numStack.size : " + this.numStack.size()
					+ " symbolStack.size : " + this.symbolStack.size());

			float value1 = (float) this.numStack.pop();
			float value2 = (float) this.numStack.pop();
			char cSymbol = (char) this.symbolStack.pop();

			this.numStack.push(operate(value1, value2, cSymbol));
		}
	}

	
	private float operate (float value1, float value2, char operator) {

		if (operator == '+') {
			return value2 + value1;
		} else if (operator == '*') {
			return value2 * value1;
		} else if (operator == '/') {
			return value2 / value1;
		} else {
			throw new RuntimeException("No this operator : " + operator);
		}

	}
	
	
}
