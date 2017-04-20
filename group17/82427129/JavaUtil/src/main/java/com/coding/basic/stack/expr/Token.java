package com.coding.basic.stack.expr;

import java.util.HashMap;

/**
 * 表达式中的数字或者操作符的抽象实体类
 * 
 * @author Walker
 *
 */
public class Token {
	private final static HashMap<String, Integer> priority = new HashMap<>(4);
	static {
		priority.put("+", 1);
		priority.put("-", 1);
		priority.put("*", 2);
		priority.put("/", 2);
	}
	private String value;

	public static final int NUMBER = 1;
	public static final int OPERA = 2;
	private int type;

	public Token(String v, int t) {
		this.value = v;
		this.type = t;
	}

	public boolean isNum() {
		return type == NUMBER;
	}

	public boolean isOpera() {
		return type == OPERA;
	}

	@Override
	public String toString() {
		return value;
	}

	public float getNumValue() {
		return Float.parseFloat(value);
	}

	public boolean hasLowerPriority(Token t) {
		if (!this.isOpera() || !t.isOpera()) {
			throw new RuntimeException("numbers can't compare priority");
		}
		return priority.get(this.value) - priority.get(t.value) < 0;
	}
	public boolean hasHigherPriority(Token t) {
		if (!this.isOpera() || !t.isOpera()) {
			throw new RuntimeException("numbers can't compare priority");
		}
		return priority.get(this.value) - priority.get(t.value) > 0;
	}
	public boolean hasEqualPriority(Token t) {
		if (!this.isOpera() || !t.isOpera()) {
			throw new RuntimeException("numbers can't compare priority");
		}
		return priority.get(this.value) - priority.get(t.value) == 0;
	}

	/*
	 * getter setter
	 */
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
