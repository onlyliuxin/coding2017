package com.ralf.stack.expr;

import com.ralf.stack.MyStack;

public class InfixExpr {

	String expr = null;
	private MyStack<String> operateStack = new MyStack<>();
	private MyStack<Integer> numStack = new MyStack<>();

	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {

		ExprIterator iterator = new ExprIterator(expr);

		while (iterator.hasNextOperate()) {
			String operateString = iterator.nextOperateString();
			if (numStack.isEmpty()) {
				
				numStack.push(iterator.nextNumString());
				numStack.push(iterator.nextNumString());
				operateStack.push(operateString);
				
			} else if (operateStack.peek().equals("*")
					|| operateStack.peek().equals("/")) {
				
				getOperatorAndNum();
				operateStack.push(operateString);
				numStack.push(iterator.nextNumString());
				
			} else {
				if (operateString.equals("+")
						|| operateString.equals("-")) {
					
					getOperatorAndNum();
					operateStack.push(operateString);
					numStack.push(iterator.nextNumString());
					
				}
				else {
					
					numStack.push(iterator.nextNumString());
					operateStack.push(operateString);
					
				}
			}

		}
		
		return getFinalResult(numStack,operateStack);
	}

	private void getOperatorAndNum() {
		
		String oper = operateStack.pop();
		int secondNum = numStack.pop();
		int firstNum = numStack.pop();
		numStack.push(calculate(firstNum, secondNum, oper));
	}

	private int getFinalResult(MyStack<Integer> numStack,
			MyStack<String> operateStack) {
		
		if (operateStack.isEmpty()) {
			return numStack.pop();
		}
		
		getOperatorAndNum();
		return getFinalResult(numStack, operateStack);
		
	}

	private int calculate(int firstNum, int secondNum, String oper) {
		
		int result;
		switch (oper) {
		case "+":
			result = firstNum + secondNum;
			break;

		case "-":
			result = firstNum - secondNum;
			break;
		case "*":
			result = firstNum * secondNum;
			break;
		case "/":
			result = firstNum / secondNum;
			break;

		default:
			result = 0;
			break;
		}
		return result;
	}

	public void getString() {
		
		ExprIterator iterator = new ExprIterator(expr);
		while (iterator.hasNextNum()) {
			System.out.print(iterator.nextNumString());
		}
		while (iterator.hasNextOperate()) {
			System.out.print(iterator.nextOperateString());
		}
	}

}
