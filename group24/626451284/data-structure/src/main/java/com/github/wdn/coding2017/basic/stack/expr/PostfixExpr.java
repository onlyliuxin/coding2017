package com.github.wdn.coding2017.basic.stack.expr;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PostfixExpr {
String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		try{
			Stack stack = new Stack();
			char[] exprArr = expr.toCharArray();
			String numStr="";
			for (int i = 0; i < exprArr.length; i++) {
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
					String first = stack.pop().toString();
					String second = stack.pop().toString();
					float result = Operator.calculate(Float.parseFloat(second), Float.parseFloat(first), c+"");
					stack.push(result);
				}
			}
			return Float.parseFloat(stack.pop().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0f;
	}


}
