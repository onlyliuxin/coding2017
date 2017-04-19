package com.coding.basic.stack.expr;

public class Token {
	
	public static final int OPRATOR = 0;
	public static final int NUMBER = 1;

	private int signal;
	private String value;
	
	public Token(int signal, String digit) {
		this.signal = signal;
		this.value = digit;
	}

	public boolean isOperator() {
		return this.signal == Token.OPRATOR;
	}
	
	public boolean isDigit() {
		return this.signal == Token.NUMBER;
	}
	
	public int getSignal() {
		return signal;
	}

	public void setSignal(int signal) {
		this.signal = signal;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
	
	
}
