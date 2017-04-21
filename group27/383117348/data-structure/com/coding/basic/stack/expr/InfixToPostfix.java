package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.coding.basic.stack.Stack;


public class InfixToPostfix {

	public static List<Token> convert(String expr) {
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		List<Token> result = getPostOrder(tokens);	
		
		return result;
	}

	/**
	 * 中序表达式转后序表达式
	 * 
	 * @param list
	 * @return
	 */
	private static List<Token> getPostOrder(List<Token> tokens) {

		List<Token> result = new ArrayList<Token>();
		Stack stack = new Stack();
		for (int i = 0; i < tokens.size(); i++) {
			// 如果为数字,加到集合里
			if (tokens.get(i).isNumber()) {
				result.add(tokens.get(i));
			} else {
				Token token = null;
				if(!stack.isEmpty()){
					token = (Token)stack.peek();
				}
				while(!stack.isEmpty() && token.hasHigherPriority(tokens.get(i))){
					result.add((Token)stack.pop());
				}
				stack.push(tokens.get(i));
			}
		}
		while (!stack.isEmpty()) {
			// 最后看下操作符栈还有操作符没,有了加到集合末尾
			result.add((Token) stack.pop());
		}
		return result;
	}	
	
	
	
	public static void main(String[] args) {
		List<Token> list = InfixToPostfix.convert("300*20+12*5-20/4");
		Assert.assertEquals("[300, 20, *, 12, 5, *, +, 20, 4, /, -]", list.toString());
	}
}