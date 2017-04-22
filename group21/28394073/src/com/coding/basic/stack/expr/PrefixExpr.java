package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	
	public float evaluate() {
		Stack<Float> num = new Stack<Float>();
		
		//将前缀表达式存到arrayList里面；
		List<String> list = ExprUtil.parseExpr(expr);
		System.out.print(list);
		//从右往左遍历arrayList,遇到数字push, 遇到符号pop并计算，然后再push回栈；
		int i = list.size();
		while(i--!=0){
			String item = list.get(i);
			if(ExprUtil.isNum(item)){
				num.push(Float.parseFloat(item));
			}else{
				Float n1 = num.pop();
				Float n2 = num.pop();
				Float n3= ExprUtil.Calculate(n1, n2, item);
				num.push(n3);
			}
		}
		return num.pop();
	}
	
}
