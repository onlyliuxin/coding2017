package com.coding.basic.stack.expr;

import com.coding.basic.stack.Stack;
import com.coding.basic.stack.StackUtil;
import com.coding.basic.stack.expr.util.ExprIterator;
import com.coding.basic.stack.expr.util.FixExprUtil;
import com.coding.basic.stack.expr.util.Operator;

public class InfixExpr {

	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {

		//数据栈
		Stack<Float> dataStack = new Stack<>();
		//操作栈
		Stack<Operator> operStack = new Stack<>();
		
		parseExpr(dataStack,operStack);
		
		return summary(dataStack,operStack);
	}

	
	/**
	 * 拆分数据与运算符于两个栈中
	 * 
	 * @return
	 */
	private void parseExpr(Stack<Float> dataStack,Stack<Operator> operStack) {

		ExprIterator it = new ExprIterator(expr);
		while(it.hasNext()){
			String element = it.next();
			if (Operator.contains(element)) {
				putOpersToStack(dataStack,operStack, element);
			} else {
				dataStack.push(Float.parseFloat(element));
			}
		}
		validationLastOpers(dataStack,operStack);
	}

	/**
	 * 检查最后一个操作符优先级与之前是否保持统一，
	 * 若不统一则进行运算,使栈中运算符保持同一个优先级
	 * @param stacks
	 */	
	private void validationLastOpers(Stack<Float> dataStack,Stack<Operator> operStack) {
		
		Object[] opers = StackUtil.getTop(operStack, 2);
		Operator thisOper = (Operator) opers[0];
		Operator preOper = (Operator) opers[1];
		if(thisOper.getLevel()!=preOper.getLevel()){			
			calculateToStack(dataStack,operStack,false);
		}		
	}

	/**
	 * 运算符压栈
	 * @param stacks
	 * @param c
	 */
	private void putOpersToStack(Stack<Float> dataStack,Stack<Operator> operStack, String c) {

		Operator thisOper = Operator.getOperator(c);		
		if (!operStack.isEmpty()) {
			Operator preOper = operStack.peek();
			if (preOper.getLevel() > thisOper.getLevel()) {	
				calculateToStack(dataStack,operStack,false);
			}
		}
		operStack.push(thisOper);
	}
	
	/**
	 * 运算距栈顶最近两个元素的值并压回原栈
	 * @param stacks 
	 * stacks[0]运算符,stacks[1]数据
	 * @param isReverse 
	 * 当isReversed为true时会交换两个元素的位置
	 */
	private void calculateToStack(Stack<Float> dataStack,Stack<Operator> operStack,boolean isReverse){
				
		float a,b;
		if(isReverse){
			a = dataStack.pop();
			b = dataStack.pop();;
		}else{
			b = dataStack.pop();;
			a = dataStack.pop();;
		}
				
		Operator oper = operStack.pop();
		float res = FixExprUtil.calculate(a,oper,b);
		dataStack.push(res);
	}

	/**
	 * 汇总结果
	 * @param stacks
	 * @return
	 */
	private float summary(Stack<Float> dataStack,Stack<Operator> operStack) {
		
		StackUtil.reverse(dataStack);
		StackUtil.reverse(operStack);
		
		while (!operStack.isEmpty()) {
			calculateToStack(dataStack,operStack,true);
		}
		return dataStack.pop();
	}
	
}
