package com.coding.basic.stack.expr;

import com.coding.basic.stack.Stack;
import com.coding.basic.stack.expr.util.ExprIterator;
import com.coding.basic.stack.expr.util.FixExprUtil;
import com.coding.basic.stack.expr.util.Operator;

public class PrefixExpr {

    String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		
		//数据栈
		Stack<Float> dataStack = new Stack<>();
		
		parseExpr(dataStack,null);
		
		return dataStack.peek();
	}
	
	/**
	 * 解析表达式
	 * @return
	 */
	private void parseExpr(Stack<Float> dataStack,Stack<Operator> operStack) {

		ExprIterator it = new ExprIterator(FixExprUtil.reverse(expr));
		float a,b,res;
		while(it.hasNext()){
			String element = it.next();
			if (Operator.contains(element)) {
				a = dataStack.pop();
				b = dataStack.pop();
				res = FixExprUtil.calculate(a, Operator.getOperator(element), b);
				dataStack.push(res);
			} else {
				dataStack.push(Float.parseFloat(element));
			}
		}
	}
	
}
