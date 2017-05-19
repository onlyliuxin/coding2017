package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		TokenParser parser = new TokenParser();
		List<Token> infixExpr_LToken = parser.parse(expr);
		
		List<Token> postfixExpr_LToken = new ArrayList<Token>();
		Stack<Token> operatorStack_SToken = new Stack<Token>();
		for(Token token : infixExpr_LToken){
			if(token.isNumber()){
				postfixExpr_LToken.add(token);
			}else{
				while(!operatorStack_SToken.isEmpty() && 
						!token.hasHigherPriority(operatorStack_SToken.peek())){
					//运算符栈不为空 且 当前运算符的优先级 小于等于 栈顶运算符的优先级
					postfixExpr_LToken.add(operatorStack_SToken.pop());
				}
				operatorStack_SToken.push(token);
			}
		}
		
		//此时运算符栈 栈底到栈顶 运算符的优先级 严格递增
		while(!operatorStack_SToken.isEmpty()){
			postfixExpr_LToken.add(operatorStack_SToken.pop());
		}
		return postfixExpr_LToken;
	}
	
	

}
