package com.coding.basic.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Token {
	public static final List<String> OPERATORS = Arrays.asList("+", "-", "*", "/");
	private static final Map<String,Integer> priorities = new HashMap<>();
	static {
		priorities.put("+", 1);
		priorities.put("-", 1);
		priorities.put("*", 2);
		priorities.put("/", 2);
	}
	
	/**符号*/
	static final int OPERATOR = 1;
	
	/** 数字 */
	static final int NUMBER = 2;
	String value;
	int type;
	public Token(int type, String value){
		this.type = type;
		this.value = value;
	}		

	/**
	 * 判断是数字 
	 * @return 是，返回true;
	 * 		<br>否，返回false
	 */
	public boolean isNumber() {
		return type == NUMBER;
	}

	/**
	 * 判断是运算符
	 *  @return 是，返回true;
	 * 		<br>否，返回false
	 */
	public boolean isOperator() {
		return type == OPERATOR;
	}

	public int getIntValue() {
		return Integer.valueOf(value).intValue();
	}
	public String toString(){
		return value;
	}
	
	/**
	 * 取优先级
	 * @param t 
	 * @return 优先级高则返回true，反之则false
	 */
	public boolean hasHigherPriority(Token t){
		if(!this.isOperator() && !t.isOperator()){
			throw new RuntimeException("numbers can't compare priority");
		}
		return priorities.get(this.value) - priorities.get(t.value) > 0;
	}
	
	

}