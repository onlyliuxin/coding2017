package algorithm;

import datastructure.basic.Stack;

import java.util.ArrayList;
import java.util.List;

public class InfixExpr {

	Stack numbers = new Stack();
	Stack operators = new Stack();

	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
		operators.push("#");
	}

	public float evaluate() throws CalculateException {
		numbers.clear();
		operators.clear();
		operators.push("#");

		String[] split = split(expr);
		String[] strs = new String[split.length + 1];
		strs[strs.length - 1] = "#";
		System.arraycopy(split, 0, strs, 0, split.length);

		for (int i = 0; !operators.isEmpty() && i < strs.length; ++i) {
			String str = strs[i];
			try {
				float number = Float.parseFloat(str);
				putNumber(number);
			} catch (NumberFormatException e) {
				putOperator(str);
			}
		}
		return numbers.isEmpty() ? 0 : (float) numbers.peek();
	}

	private String[] split(String expr) {
		List<String> list = new ArrayList<>();
		int startPos = 0;
		for (int i = 0, j = 1; j < expr.length(); ++i, ++j) {
			char cI = expr.charAt(i);
			char cJ = expr.charAt(j);
			if (isDigit(cI) ^ isDigit(cJ)) {
				list.add(expr.substring(startPos, j));
				startPos = j;
			}
		}
		list.add(expr.substring(startPos));
		return list.toArray(new String[list.size()]);
	}

	private boolean isDigit(char c) {
		return Character.isDigit(c) || c == '.';
	}

	private void putNumber(float num) {
		numbers.push(num);
	}

	private void putOperator(String op) throws CalculateException {
		int compare = compare(op, (String) operators.peek());
		switch (compare) {
			case 1:
				operators.push(op);
				break;
			case 0:
				operators.pop();
				break;
			case -1:
				float num1 = (float) numbers.pop();
				float num2 = (float) numbers.pop();
				String operator = (String) operators.pop();
				float result = calculate(num2, operator, num1);
				numbers.push(result);
				putOperator(op);
				break;
		}
	}

	private float calculate(float num2, String op, float num1) throws CalculateException {
		switch (op) {
			case "+":
				return num2 + num1;
			case "-":
				return num2 - num1;
			case "*":
				return num2 * num1;
			case "/":
				if (num1 != 0) {
					return num2 / num1;
				}
		}
		throw new CalculateException();
	}

	private int compare(String op, String peek) {
		int opIndex = indexOf(op);
		int peekIndex = indexOf(peek);
		return table[opIndex][peekIndex];
	}

	private int indexOf(String op) {
		switch (op) {
			case "+":
				return 0;
			case "-":
				return 1;
			case "*":
				return 2;
			case "/":
				return 3;
			case "(":
				return 4;
			case ")":
				return 5;
			case "#":
				return 6;
		}
		return 0;
	}

	//优先级表
	private int[][] table = {
		//    +   -   *   /   (   )   #
			{-1, -1, -1, -1,  1, -1,  1}, //  +
			{-1, -1, -1, -1,  1, -1,  1}, //  -
			{ 1,  1, -1, -1,  1, -1,  1}, //  *
			{ 1,  1, -1, -1,  1, -1,  1}, //  /
			{ 1,  1,  1,  1,  1, -1,  1}, //  (
			{-1, -1, -1, -1, -1,  0,  1}, //  )
			{-1, -1, -1, -1, -1, -1,  0}  //  #
	};

	public static class CalculateException extends Exception {}
}
