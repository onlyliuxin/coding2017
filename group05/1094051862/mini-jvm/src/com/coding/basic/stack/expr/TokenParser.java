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
			} else{
				System.out.println("char :["+c+"] is not number or operator,ignore");
				i++;
			}
		}
		
		return tokens;
	}

	private  int indexOfNextOperator(int i, String expr) {
		while (Character.isDigit(expr.charAt(i))) {
			i++;
			if (i == expr.length()) {
				break;
			}
		}
		return i;
	}

	private boolean charIsDigit(char c) {
		//return Character.isDigit(c);
		return c>='0' && c<='9';
	}

	private boolean charIsOperator(char c) {
		String sc = String.valueOf(c);
		return Token.OPERATORS.contains(sc);
	}
	
}
