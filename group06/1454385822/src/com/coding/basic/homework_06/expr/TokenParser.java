package com.coding.basic.homework_06.expr;

import java.util.ArrayList;
import java.util.List;

public class TokenParser {

	public List<Token> parse(String expr){
		List<Token> tokens = new ArrayList<Token>();
		int i = 0;
		while(i < expr.length()){
			char c = expr.charAt(i);
			if (isOperator(c)) {
				Token t = new Token(Token.OPERATOR, String.valueOf(c));
				tokens.add(t);
				i++;
			} else if(Character.isDigit(c)){
				int nextOperatorIndex = indexOfNextOperatorSpace(i, expr);
				if(nextOperatorIndex == i){
					nextOperatorIndex = i + 1;
				}
				String value = expr.substring(i, nextOperatorIndex);
				System.out.println(value);
				Token token = new Token(Token.NUMBER, value);
				tokens.add(token);
				i = nextOperatorIndex;
			}else if(Character.isSpaceChar(c)){
				i++;
				continue;
			}else{
				System.out.println("char :["+c+"] is not number or operator,ignore");
				i++;
			}
		}
		return tokens;
	}
	
	
	private int indexOfNextOperatorSpace (int i, String expr){
		while(Character.isDigit(expr.charAt(i))){
			i++;
			if (i == expr.length()) {
				break;
			}
		}
		return i;
	}
	
	private boolean isOperator(char c){
		String sc = String.valueOf(c);
		return Token.OPERATORS.contains(sc);
	}
	
}
