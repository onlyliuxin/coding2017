package com.ralf.stack.expr;

import java.util.ArrayList;
import java.util.List;

public class TokenParser {
	
	public TokenParser(){

	}
	public List<Token> parse(String string) {
		List<Token> tokens = new ArrayList<Token>();
		int i = 0;
		while(i < string.length()){
			
			char ch = string.charAt(i);
			if (isOperator(ch)) {
				Token token = new Token(String.valueOf(ch), Token.OPERATOR);
				tokens.add(token);
				i++;
			}
			else if (Character.isDigit(ch)) {
				int nextIndexOfChar = nextIndexOfOperator(i,string);
				String value = string.substring(i, nextIndexOfChar);
				Token token = new Token(value, Token.NUMBER);
				tokens.add(token);
				i = nextIndexOfChar;
			}
			else {
				System.out.println("char:" + ch + " is not a number or operator,ignore!");
				i++;
			}

		}
		
		
		return tokens;
	}

	private int nextIndexOfOperator(int i, String string) {
		
		while(Character.isDigit(string.charAt(i))){
			i++;
			if (i == string.length()) {
				break;
			}
		}
		return i;
	}

	private boolean isOperator(char ch) {
		String string = String.valueOf(ch);
		return Token.OPERATORS.contains(string);
	}

}
