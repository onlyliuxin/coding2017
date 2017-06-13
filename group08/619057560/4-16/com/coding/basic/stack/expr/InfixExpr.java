package com.coding.basic.stack.expr;

import com.coding.basic.stack.Stack;

public class InfixExpr {
	String expr = null;
	
	Stack operatorStack = new Stack();
	Stack operandStack = new Stack();
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}
	
	private boolean isOperator(char exprChar) {
		if (exprChar == '+' || exprChar == '-' || exprChar == '*' || exprChar == '/') {
			return true;
		}
		else {
			return false;
		}
	}
	
	private float shiftOperator(float newOperand) {
		if (operatorStack.isEmpty()) {
			return newOperand;
		}
		Character lastOperatorInStack = (Character)(operatorStack.pop());
		switch (lastOperatorInStack.charValue()) {
		case '-':
			operatorStack.push('+');
			return newOperand * (-1);
		case '/':
			operatorStack.push('*');
			return 1 / newOperand;
		default:
			operatorStack.push(lastOperatorInStack);
			return newOperand;
		}
	}
	
	private boolean needToProcessCalcWithHigherPriority(Character operator) {
		if (operator == null || operatorStack.isEmpty()) {
			return false;
		}
		
		Character lastOperatorInStack = (Character)(operatorStack.peek());
		if ((operator == '+' || operator == '-') && 
				(lastOperatorInStack == '*' || lastOperatorInStack == '/')) {
			return true;
		} else {
			return false;
		}
	}
	
	private float processCalc(Float arg2) throws InfixExprException {
		if (operandStack.isEmpty() || operatorStack.isEmpty()) {
			throw new InfixExprException();
		}
		
		Float arg1 = (Float)operandStack.pop();
		char operator = ((Character)(operatorStack.pop())).charValue();
		switch (operator) {
		case '+':
			return arg1 + arg2;
		case '-':
			return arg1 - arg2;
		case '*':
			return arg1 * arg2;
		case '/':
			return arg1 / arg2;
		default:
			throw new InfixExprException();
		}
	}
	
	private void onNewOperandFound(Character exprChar, Float newOperand) throws InfixExprException {
		newOperand = shiftOperator(newOperand);
		while (needToProcessCalcWithHigherPriority(exprChar)) {
			newOperand = processCalc(newOperand);
			operandStack.push(newOperand);
			newOperand = null;
		}
		if (newOperand != null) {
			operandStack.push(newOperand);
		}
	}
	
	private void calculateWithStacks() throws InfixExprException {
		while (!operatorStack.isEmpty()) {
			operandStack.push(processCalc((Float)operandStack.pop()));
		}
		
		if (operandStack.isEmpty()) {
			throw new InfixExprException();
		}
	}

	private void constructStacks() throws InfixExprException {
		int operandStart = 0;
		for (int i = 0; i < expr.length(); i++) {
			char exprChar = expr.charAt(i);
			if (isOperator(exprChar)) {
				onNewOperandFound(exprChar, Float.parseFloat(expr.substring(operandStart, i)));
				operatorStack.push(exprChar);
				operandStart = i + 1;
			}
		}
		onNewOperandFound(null, Float.parseFloat(expr.substring(operandStart)));
	}

	public float evaluate() {
		try {
			constructStacks();
			
			calculateWithStacks();
			
			return (Float)operandStack.pop();
		} catch (InfixExprException e) {
			e.printStackTrace();
		}
		return 0.0f;
	}

}
