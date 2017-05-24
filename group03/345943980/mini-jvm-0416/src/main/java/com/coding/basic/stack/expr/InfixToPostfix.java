package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序表达式转后缀表达式
 * @author chenming E-mail:cm_20094020@163.com
 * @version 创建时间：2017年5月7日 下午4:29:06
 */
public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		List<Token> inFixTokens = new TokenParser().parse(expr);
		List<Token> postFixTokens = new ArrayList<>();
		Stack<Token> opStack = new Stack<Token>();
		for(Token token : inFixTokens){
			if(token.isOperator()){
				while(!opStack.isEmpty() 
						&& !token.hasHigherPriority(opStack.peek())){
					postFixTokens.add(opStack.pop());					
				}
				opStack.push(token);		
			}
			if(token.isNumber()){
				postFixTokens.add(token);
			}
		}
		while(!opStack.isEmpty()){
			postFixTokens.add(opStack.pop());	
		}
		return postFixTokens;
	}
}
