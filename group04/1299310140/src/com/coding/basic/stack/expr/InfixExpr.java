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
		List<Token> expr_LToken = parser.parse(this.expr);
		
		Stack<Float> operandStack_SF = new Stack<Float>();
		Stack<Token> operatorStack_SToken = new Stack<Token>();
		for(Token token : expr_LToken){
			if(token.isNumber()){
				operandStack_SF.push(new Float(token.getIntValue()));
			}else{
				while(!operatorStack_SToken.isEmpty() && 
						!token.hasHigherPriority(operatorStack_SToken.peek())){
					//运算符栈不为空 且 当前运算符的优先级 小于等于 栈顶运算符的优先级
					Float f2 = operandStack_SF.pop();
					Float f1 = operandStack_SF.pop();
					String operator = operatorStack_SToken.pop().toString();
					Float result = CalculateUtil.calculate(operator, f1, f2);
					operandStack_SF.push(result);
				}
				operatorStack_SToken.push(token);
			}
		}
		
		//此时运算符栈 栈底到栈顶 运算符的优先级 严格递增
		while(!operatorStack_SToken.isEmpty()){
			Float f2 = operandStack_SF.pop();
			Float f1 = operandStack_SF.pop();
			String operator = operatorStack_SToken.pop().toString();
			Float result = CalculateUtil.calculate(operator, f1, f2);
			operandStack_SF.push(result);
		}
		return operandStack_SF.pop().floatValue();
	}
	
	
}
