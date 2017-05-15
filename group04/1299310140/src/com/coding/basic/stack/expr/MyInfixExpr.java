package com.coding.basic.stack.expr;

import java.util.Stack;

public class MyInfixExpr {
	//操作数为整数，操作符只支持+、-、*、/，/的返回值为float类型
	private String expr = "";
	private char[] sc = null;
	private int pos = 0;
	
	public MyInfixExpr(String expr) {
		this.expr = expr;
		this.sc = expr.toCharArray();
	}
	
	//下一个操作数
	private int nextOperand(){
		int result = sc[pos] - 48;//char:'0'-->int:0
		pos++;
		while(pos < sc.length && sc[pos] >= '0' && sc[pos] <= '9'){
			result = result * 10 + (sc[pos] - 48);
			pos++;
		}
		return result;
	}
	
	//下一个操作符
	private char nextOperator(){
		return sc[pos++];
	}

	public float evaluate() {		
		Stack<Number> stack = this.multiplyDivide();
		float result = add(stack);
		return result;
	}
	
	//将栈内所有操作数相加
	private float add(Stack<Number> stack){
		if(stack == null || stack.isEmpty()){
			return 0;
		}
		
		float result = 0;
		while(!stack.isEmpty()){
			result = result + (float)stack.pop();
		}
		return result;
	}
	
	//操作数入栈，完成乘除运算，'-'随数入栈
	private Stack<Number> multiplyDivide(){
		Stack<Number> operandStack = new Stack<Number>();
		operandStack.push((float)this.nextOperand());
		int tempOperand = 0;
		char tempOperator = 0;
		while(pos < sc.length){
			tempOperator = this.nextOperator();
			tempOperand = this.nextOperand();
			if(tempOperator == '+'){
				operandStack.push((float)tempOperand);
			}else if(tempOperator == '-'){
				operandStack.push((float)(- tempOperand));
			}else if(tempOperator == '*'){
				float top = (float)operandStack.pop();
				operandStack.push(top * tempOperand);
			}else if(tempOperator == '/'){
				float top = (float)operandStack.pop();
				operandStack.push(top / tempOperand);
			}
		}
		
		return operandStack;
	}
}
