package com.github.wdn.coding2017.basic.stack.expr;

import java.util.Arrays;
import java.util.Stack;

public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}
	public float evaluate() {
		try {
			Stack stack = new Stack();
			char[] exprArr = expr.toCharArray();
			String numStr="";
			for (int i = exprArr.length - 1; i >= 0; i--) {
				char c = exprArr[i];
				if(!Operator.isOperator(c) && c!=' '){
					numStr = numStr+c;
					continue;
				}
				if(!"".equals(numStr)){
					stack.push(numStr);
					numStr="";
				}
				if (Operator.isOperator(c)) {
					float result = Operator.calculate(Float.parseFloat(stack.pop().toString()), Float.parseFloat(stack.pop().toString()), c+"");
					stack.push(result);
				}
			}
			return Float.parseFloat(stack.pop().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
