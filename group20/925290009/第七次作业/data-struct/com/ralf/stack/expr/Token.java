package com.ralf.stack.expr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Token {

	public static final List<String> OPERATORS = Arrays.asList("+","-","*","/");
	private static final Map<String, Integer> priorities = new HashMap<String, Integer>();
	static{
		priorities.put("+", 1);
		priorities.put("-", 1);
		priorities.put("*", 2);
		priorities.put("/", 2);
	}
	static final int NUMBER = 1;
	static final int OPERATOR = 2;
	String value;
	int type;
	
	public Token(String value, int type){
		this.value = value;
		this.type = type;
	}
	public int getValue() {
		
		return Integer.valueOf(value).intValue();
	}
	public String toString(){
		return value;
	}
	public boolean isNumber(){
		return type == NUMBER;
	}
	public boolean isOperator(){
		return type == OPERATOR;
	}
	public boolean hasHigherPriority(Token token){
		
		if (!this.isOperator() || !token.isOperator()) {
			throw new RuntimeException("numbers can't compare priority");
		}
		return priorities.get(this.value) - priorities.get(token.value) > 0;
		
	}

}
