package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {
String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		
		Stack<Float> num = new Stack<Float>();
		
		//将后缀表达式存到arrayList里面；
		List<String> list = ExprUtil.parseExpr(expr);
		System.out.print(list);
		
		//从左往右遍历arrayList,遇到数字push, 遇到符号pop并计算，然后再push回栈；
		int i = 0;
		while(i<list.size()){
			String item = list.get(i++);
			if(ExprUtil.isNum(item)){
				num.push(Float.parseFloat(item));
			}else{
				Float n1 = num.pop();
				Float n2 = num.pop();
				Float n3= ExprUtil.Calculate(n2, n1, item);
				num.push(n3);
			}
		}
		return num.pop();

	}
	
	
}
