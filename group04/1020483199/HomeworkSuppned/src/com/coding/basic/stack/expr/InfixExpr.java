package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;


public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		
		
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(this.expr);
		
		//存放操作符栈
		Stack<Token> opStack = new Stack<>();
		//操作数据值栈
		Stack<Float> numStack = new Stack<>();
		
		for(Token token : tokens){
			
			if (token.isOperator()){
				
				while(!opStack.isEmpty() 
						&& !token.hasHigherPriority(opStack.peek())){
					Token prevOperator = opStack.pop();
					Float f2 = numStack.pop();
					Float f1 = numStack.pop();
					Float result = calculate(prevOperator.toString(), f1,f2);
					numStack.push(result);						
					
				}
				opStack.push(token);
			} 
			if(token.isNumber()){
				numStack.push(new Float(token.getIntValue()));
			}
		}
		
		while(!opStack.isEmpty()){
			Token token = opStack.pop();
			Float f2 = numStack.pop();
			Float f1 = numStack.pop();
			numStack.push(calculate(token.toString(), f1,f2));
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
