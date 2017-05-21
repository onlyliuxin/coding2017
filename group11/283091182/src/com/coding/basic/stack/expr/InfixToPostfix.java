package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;

import com.coding.basic.stack.Stack;

public class InfixToPostfix {
	private static final String REGEX_OPR = "[\\+\\-\\*\\/]";
	
	public static List<Token> convert(String expr) {
		TokenParser parser = new TokenParser();
		List tokens = parser.parse(expr);
		List<Token> result = new ArrayList<Token>();
		
		Stack ops = new Stack();
		
		while(!tokens.isEmpty()){
			Token t = getAndRemoveNextToekn(tokens);
			
			if(t.isOperator()){
				while(!ops.isEmpty()&&(!t.hasHigherPriority((Token)ops.peek()))){
						result.add((Token)ops.pop());
						System.out.println("pushed:"+ops);
				}
				ops.push(t);
			}

			if(t.isNumber()){
				result.add(t);
			}
		}
		
		while(!ops.isEmpty()){
			result.add((Token)ops.pop());
		}
		return result;
	}
	
	private static Token getAndRemoveNextToekn(List<Token> tokens){
		return tokens.remove(0);
	}
	
}
