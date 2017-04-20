package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;



public class TokenParser {

	public List<Token> parse(String expr) {
		
		List<Token> tokens = new ArrayList<Token>();
		int i = 0;
		while (i < expr.length()) {
			char c = expr.charAt(i);
			if (charIsOperator(c)) {
				Token t = new Token(Token.OPRATOR, String.valueOf(c));
				tokens.add(t);
				i ++;
			} else if (charIsDigit(c)) {
				int nextOperatorIndex = indexOfNextOperator(i,expr);
				String value = expr.substring(i, nextOperatorIndex);
				Token t = new Token(Token.NUMBER, value);
				tokens.add(t);
				i = nextOperatorIndex;
			}
			System.out.println(c);
		}
		
		return tokens;
	}

	private int indexOfNextOperator(int nowIndex, String expr) {
		for (int i = nowIndex; i < expr.length(); i++) {
			char c = expr.charAt(i);
			if (charIsOperator(c)) {
				return i;
			}
		}
		return expr.length();//如果后面没有操作符，返回字符串长度，用于截取数字
	}

	private boolean charIsDigit(char c) {
		return c>='0' && c<='9';
	}

	private boolean charIsOperator(char c) {
		
		return c=='+' || c=='-' || c=='*' || c=='/';
	}
	
}
