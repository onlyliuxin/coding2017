package com.coding.basic.expr;

import java.util.ArrayList;
import java.util.List;

public class TokenParser {
	
	
	public static  List<Token> parse(String expr) {
		List<Token> tokens = new ArrayList<>();

		int i = 0;

		while (i < expr.length()) {

			char c = expr.charAt(i);

			if (isOperator(c)) {

				Token t = new Token(Token.OPERATOR, String.valueOf(c));
				tokens.add(t);
				i++;

			} else if (Character.isDigit(c)) {

				int nextOperatorIndex = indexOfNextOperator(i, expr);
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

	private static  int indexOfNextOperator(int i, String expr) {

		while (Character.isDigit(expr.charAt(i))) {
			i++;
			if (i == expr.length()) {
				break;
			}
		}
		return i;

	}

	private static  boolean isOperator(char c) {
		String sc = String.valueOf(c);
		return Token.OPERATORS.contains(sc);
	}
	
	public static Token calculate(Token operator, Token number1, Token number2) {
		if(operator==null||number1==null||number2==null){
			return null;
		}
		String op = operator.value;
		if(op.equals("+")){
			return new Token(Token.NUMBER, String.valueOf(number1.getFloatValue()+number2.getFloatValue()));
		}
		if(op.equals("-")){
			return new Token(Token.NUMBER, String.valueOf(number1.getFloatValue()-number2.getFloatValue()));
		}
		if(op.equals("*")){
			return new Token(Token.NUMBER, String.valueOf(number1.getFloatValue()*number2.getFloatValue()));
		}
		if(op.equals("/")){
			return new Token(Token.NUMBER, String.valueOf(number1.getFloatValue()/number2.getFloatValue()));
		}
		throw new RuntimeException(op + " is not supported");
	}
}
