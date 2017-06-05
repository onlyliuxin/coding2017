package com.coding.basic.stack.expr;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
public class InfixToPostfix {
	static final Map<String,Integer> priority=new HashMap<>();
	static{
		priority.put("+", 1);
		priority.put("-", 1);
		priority.put("*", 2);
		priority.put("/", 2);
		
	}
	public static List<Token> convert(String expr) {
		String answer="";
		Stack<String> stack=new Stack<>();
		TokenParser parser = new TokenParser();
		List<Token> tokens  = parser.parse(expr);
		Iterator< Token> iter=tokens.iterator();
		while(iter.hasNext()){
			Token token=iter.next();
			if(token.isNumber())answer+=token.getIntValue()+" ";
			else if(token.isOperator()) {
				String op=token.toString();
				while(!stack.isEmpty()){
					int peek=priority.get(stack.peek().toString());
					int cur=priority.get(op);
					if(peek>=cur)answer+=stack.pop()+" ";
					if(peek<cur)break;
				}
				stack.push(op);
			}
		}
		while(!stack.isEmpty()){
			answer+=stack.pop()+" ";
		}
		tokens=parser.parse(answer);
		return tokens;
	}
	
	

}
