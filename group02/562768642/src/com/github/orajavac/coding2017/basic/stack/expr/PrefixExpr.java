package com.github.orajavac.coding2017.basic.stack.expr;

import java.util.HashMap;
import java.util.Map;
import com.github.orajavac.coding2017.jvm.util.Util;
import com.github.orajavac.coding2017.basic.stack.Stack;

/*
 * 前序表达式
 */
public class PrefixExpr {
	
	String expr = null;
	
	private final String ADD = "+";
	
	private final String SUB = "-";
	
	private final String MUL = "*";
	
	private final String DIV = "/";
	
	private Map<String,Integer> operator = new HashMap<String,Integer>();
	
	private Stack num = new Stack();
	
	public PrefixExpr(String expr) {
		this.expr = expr;
		this.operator.put(this.ADD,1);
		this.operator.put(this.SUB,1);
		this.operator.put(this.MUL,2);
		this.operator.put(this.DIV,2);
	}

	public float evaluate() {
		String[] expr = Util.parseOperatorToArray(this.expr);
		/**
		 * 4*2 + 6+9*2/3 -8
		 * - + + 6 / *2 9 3 * 4 2 8
		 */
		for (int i=expr.length-1;i>=0;i--){
			if (this.operator.containsKey(expr[i])){
				num.push(calculate(expr[i],
						Float.parseFloat(num.pop().toString()),
						Float.parseFloat(num.pop().toString())));
			}else{
				num.push(expr[i]);
			}
		}
		return Float.parseFloat(num.pop().toString());
	}
	
	private Float calculate(String op, Float f1, Float f2){
		if(op.equals("+")){
			return f1+f2;
		}
		if(op.equals("-")){
			return f1-f2;
		}
		if(op.equals("*")){
			return f1*f2;
		}
		if(op.equals("/")){
			return f1/f2;
		}
		throw new RuntimeException(op + " is not supported");
	}
}
