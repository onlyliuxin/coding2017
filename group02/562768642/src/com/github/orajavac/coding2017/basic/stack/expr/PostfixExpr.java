package com.github.orajavac.coding2017.basic.stack.expr;

import java.util.HashMap;
import java.util.Map;
import com.github.orajavac.coding2017.basic.stack.Stack;
import com.github.orajavac.coding2017.jvm.util.Util;

/*
 * 后序表达式
 */
public class PostfixExpr {
	
	String expr = null;
	
	private final String ADD = "+";
	
	private final String SUB = "-";
	
	private final String MUL = "*";
	
	private final String DIV = "/";
	
	private Map<String,Integer> operator = new HashMap<String,Integer>();
	
	private Stack num = new Stack();
	
	public PostfixExpr(String expr) {
		this.expr = expr;
		this.operator.put(this.ADD,1);
		this.operator.put(this.SUB,1);
		this.operator.put(this.MUL,2);
		this.operator.put(this.DIV,2);
	}

	public float evaluate() {
		String[] expr = Util.parseOperatorToArray(this.expr);
		for (int i=0;i<expr.length;i++){
			if (this.operator.containsKey(expr[i])){
				Float f1 = Float.parseFloat(num.pop().toString());	//6
				Float f2 = Float.parseFloat(num.pop().toString());	//10
				if(isOperator(expr[i].charAt(0))){	//对于减法，f1,f2互换
					float f3 = f2;
					f2 = f1;
					f1 = f3;					
				}
				num.push(calculate(expr[i],f1,f2));
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
	
	private boolean isOperator(char c1){
		if(c1=='+')
	        return true;
	    if(c1=='/')
	        return true;
	    if(c1=='-')
	    	return true;
	    if(c1=='*')
	    	return true;
	    return false;//其他的都返回false
	};
}
