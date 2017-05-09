package com.coding.basic.stack.myself;

import java.util.List;
import java.util.Stack;

import com.coding.basic.stack.myself.Token;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		
		//存放操作符栈
		Stack<Token> opStack = new Stack<>();
		//操作数据值栈
		Stack<Float> numStack = new Stack<>();
		
		for (Token token:tokens){
			//当前的token代表的是操作符时
			if (token.isOperator()){
				//当前操作符栈不为空并且当前操作符并不比栈顶优先级更高
				while (!opStack.isEmpty()
						 && !token.hasHigherPriority(opStack.peek())){
					Token preOperate = opStack.pop();
					float f2 = numStack.pop();
					float f1 = numStack.pop();
					float result = calculate(preOperate.toString(), f1, f2);
					numStack.push(result);
				}
				opStack.push(token);
			}
			
			if (token.isNumber()){
				numStack.push(new Float(token.getIntValue()));
			}
			
			
		}
		while (!opStack.isEmpty()){
			Token token = opStack.pop();
			Float f2 = numStack.pop();
			Float f1 = numStack.pop();
			numStack.push(calculate(token.toString(), f1,f2));
		}
		
		return numStack.pop().floatValue();
		
	}
	
	public float calculate(String op, float f1, float f2){
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
