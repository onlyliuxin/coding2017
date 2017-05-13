package com.coding.basic.stack;

import java.util.List;
import java.util.Stack;

/**

 * @Description 前缀表达式  
 * 				<br>从右向左运算，不考虑优先级 
 * 				<br>eg： - × + 3 4 5 6  => (3+4)*5-6

 * @author REEFE

 * @time 2017年4月26日

 */
public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser tokenParser = new TokenParser();
		List<Token> tokens = tokenParser.parse(expr);
		
		Stack<Token> exprStack = new Stack<>();
		Stack<Float> numStack = new Stack<>();
		
		for (Token token : tokens) {
			exprStack.push(token);
		}
		while(!exprStack.isEmpty()){
			Token t = exprStack.pop();
			if(t.isNumber()){
				numStack.push(new Float(t.getIntValue()));
			}else{
				Float f1 = numStack.pop();
				Float f2 = numStack.pop();
				numStack.push(calculate(t.toString(), f1, f2));
			}
		}
		return numStack.pop().floatValue();
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
