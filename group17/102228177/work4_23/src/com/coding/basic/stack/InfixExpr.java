package com.coding.basic.stack;
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
		
		
		Stack<Token> opStack = new Stack<>();
		Stack<Float> numStack = new Stack<>();
		
		for(Token token : tokens){
			//是运算符
			if(token.isOperator()){
				//运算符栈不为空，且当前运算符优先级比栈顶低
				while(!opStack.isEmpty() 
						&& !token.hasHigherPriority(opStack.peek())){
					Token prevOperator = opStack.pop();
					Float f2 = numStack.pop();
					Float f1 = numStack.pop();
					Float result = calculate(prevOperator.toString(), f1,f2);
					numStack.push(result);	
				}
				//否则就往运算符栈添加
				opStack.push(token);
			}
			//是数字，就忘数字栈中添加
			if(token.isNumber()){
				numStack.push(new Float(token.getIntValue()));
			}
		}
		//再运算
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