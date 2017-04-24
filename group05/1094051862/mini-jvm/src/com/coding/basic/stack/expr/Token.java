package com.coding.basic.stack.expr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Token {
	
	public static final List<String> OPERATORS = Arrays.asList("+", "-", "*", "/");
	private static final Map<String,Integer> priorities = new HashMap<>();
	static {
		priorities.put("+", 1);
		priorities.put("-", 1);
		priorities.put("*", 2);
		priorities.put("/", 2);
	}
	public static final int OPRATOR = 0;
	public static final int NUMBER = 1;

	int type;
	String value;
	
	public Token(int type, String value) {
		this.type = type;
		this.value = value;
	}

	public boolean isOperator() {
		return this.type == Token.OPRATOR;
	}
	
	public boolean isDigit() {
		return this.type == Token.NUMBER;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		return value;
	}
	
	public int getIntValue() {
		return Integer.valueOf(value).intValue();
	}
	
	public int comparePriority(Token topOperator) {
		int topOptrPriority = 0;
		int optPriority = 0;
		if (topOperator.getValue().indexOf('*')>=0 || topOperator.getValue().indexOf('/')>=0) {
			topOptrPriority = 1;
		}
		if (topOperator.getValue().indexOf('+')>=0 || topOperator.getValue().indexOf('-')>=0) {
			topOptrPriority = 0;
		}
		if (this.getValue().indexOf('+')>=0 || this.getValue().indexOf('-')>=0) {
			optPriority = 0;
		}
		if (this.getValue().indexOf('*')>=0 || this.getValue().indexOf('/')>=0) {
			optPriority = 1;
		}
		
		
		return optPriority - topOptrPriority;
	}
	
	public boolean hasHigherPriority(Token t) {
		if (!this.isOperator() || !t.isOperator()) {
			throw new RuntimeException("numbers can't compare priority");
		}
		return priorities.get(this.value) - priorities.get(t.value) > 0;
	}
	
}
