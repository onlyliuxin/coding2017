package week06.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixExpr {
	String expr = null;

	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		float result = 0;

		Stack operandStack = new Stack(); // 运算数栈
		Stack operaterStack = new Stack(); // 运算符栈

		char[] exprCharArr = expr.toCharArray();
		List<String> exprStringList = new ArrayList<String>();
		addOperandAndOperater(exprCharArr, exprStringList);

		for (int i = 0; i < exprStringList.size(); i++) {
			if (isOperand(exprStringList.get(i))) {
				operandStack.push(exprStringList.get(i));
			} else if (isOperater(exprStringList.get(i))) {
				operaterStack.push(exprStringList.get(i));
			} else {
				throw new RuntimeException("this operater has not yet implemented.");
			}

			if (operaterStack.size() == 2) {

				String operater_1 = (String) operaterStack.pop();
				String operater_2 = (String) operaterStack.pop();
				if (hasTheSameOrHighPriority(operater_2, operater_1)) {

					String operand_1 = (String) operandStack.pop();
					String operand_2 = (String) operandStack.pop();
					operation(operandStack, operater_2, operand_1, operand_2);

					operaterStack.push(operater_1);
				} else if (hasTheLowPriority(operater_2, operater_1)) {

					operandStack.push(exprStringList.get(++i));
					String operand_1 = (String) operandStack.pop();
					String operand_2 = (String) operandStack.pop();
					operation(operandStack, operater_1, operand_1, operand_2);

					operaterStack.push(operater_2);
				}
			}

			if (i == exprStringList.size() - 1) {

				String operater = (String) operaterStack.pop();
				String operand_1 = (String) operandStack.pop();
				String operand_2 = (String) operandStack.pop();
				operation(operandStack, operater, operand_1, operand_2);

				result = (float) Integer.parseInt((String) operandStack.pop());
			}
		}

		return result;
	}

	private void addOperandAndOperater(char[] exprCharArr, List<String> exprStringList) {
		for (int i = 0; i < exprCharArr.length; i++) {
			if (isOperand(exprCharArr[i])) {
				StringBuilder sb = new StringBuilder();
				sb.append(exprCharArr[i]);
				if (i < exprCharArr.length - 1) {
					while (i < exprCharArr.length - 1 && isOperand(exprCharArr[i + 1])) {
						sb.append(exprCharArr[i + 1]);
						i++;
					}
				}
				exprStringList.add(sb.toString());
			} else if (isOperater(exprCharArr[i])) {
				exprStringList.add(exprCharArr[i] + "");
			}
		}
	}

	private boolean isOperand(char c) {
		return !isOperater(c);
	}

	private boolean isOperater(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/') {
			return true;
		}
		return false;
	}

	private boolean isOperand(String c) {
		return !isOperater(c);
	}

	// 字符串相等用equals()比较.
	private boolean isOperater(String c) {
		if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")) {
			return true;
		}
		return false;
	}

	// operater_1 has the same or high priority compare with the operater_2.
	private boolean hasTheSameOrHighPriority(Object operater_1, Object operater_2) {
		if ((operater_1.equals("+") && operater_2.equals("+")) || (operater_1.equals("+") && operater_2.equals("-"))
				|| (operater_1.equals("-") && operater_2.equals("+"))
				|| (operater_1.equals("-") && operater_2.equals("-"))
				|| (operater_1.equals("*") && operater_2.equals("*"))
				|| (operater_1.equals("*") && operater_2.equals("/"))
				|| (operater_1.equals("/") && operater_2.equals("*"))
				|| (operater_1.equals("/") && operater_2.equals("/"))
				|| (operater_1.equals("*") && operater_2.equals("+"))
				|| (operater_1.equals("*") && operater_2.equals("-"))
				|| (operater_1.equals("/") && operater_2.equals("+"))
				|| (operater_1.equals("/") && operater_2.equals("-"))) {
			return true;
		}
		return false;
	}

	//// operater_1 has the low priority compare with the operater_2.
	private boolean hasTheLowPriority(Object operater_1, Object operater_2) {
		if ((operater_1.equals("+") && operater_2.equals("*")) || (operater_1.equals("+") && operater_2.equals("/"))
				|| (operater_1.equals("-") && operater_2.equals("*"))
				|| (operater_1.equals("-") && operater_2.equals("/"))) {
			return true;
		}
		return false;
	}

	private void operation(Stack operandStack, String operater, String operand_1, String operand_2) {
		if (operater.equals("+")) {
			operandStack.push((Integer.parseInt(operand_2) + Integer.parseInt(operand_1)) + "");
		} else if (operater.equals("-")) {
			operandStack.push((Integer.parseInt(operand_2) - Integer.parseInt(operand_1)) + "");
		} else if (operater.equals("*")) {
			operandStack.push((Integer.parseInt(operand_2) * Integer.parseInt(operand_1)) + "");
		} else if (operater.equals("/")) {
			operandStack.push((Integer.parseInt(operand_2) / Integer.parseInt(operand_1)) + "");
		}
	}

}
