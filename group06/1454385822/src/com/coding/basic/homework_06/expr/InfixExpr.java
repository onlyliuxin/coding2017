package com.coding.basic.homework_06.expr;

import java.util.List;
import java.util.Stack;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {	
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		Stack<Float> numStack = new Stack<Float>();
		Stack<Token> opStack = new Stack<Token>();
		
		for (Token token : tokens) {
			if(token.isOperator()){
				if(opStack.isEmpty()){
					opStack.push(token);
				}else{
					while(!opStack.isEmpty() && !token.hasHigherPriority(opStack.peek())){
						Token prevOperator = opStack.pop();
						Float float1 = numStack.pop();
						Float float2 = numStack.pop();
						Float result = getResult(float1, float2, prevOperator.toString());
						numStack.push(result);
					}
					opStack.push(token);
				}
			}
			if(token.isNumber()){
				numStack.push(new Float(token.getIntValue()));
			}
		}
		
		while(!opStack.isEmpty()){
			Token prevOperator = opStack.pop();
			Float float1 = numStack.pop();
			Float float2 = numStack.pop();
			Float result = getResult(float1, float2, prevOperator.toString());
			numStack.push(result);
		}
		
		return numStack.pop();
	}

	private Float getResult(Float float1, Float float2, String operator){
		if(operator.equals("+")){
			return float1 + float2;
		}else if(operator.equals("-")){
			return float2 - float1;
		}else if(operator.equals("*")){
			return float1 * float2;
		}else if(operator.equals("/")){
			return float2 / float1;
		}else{
			throw new RuntimeException(operator + " is not supported");
		}
		
		
	}
	

}