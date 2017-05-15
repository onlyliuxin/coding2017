package week07.basic;

import java.util.ArrayList;
import java.util.List;

public class TokenParser {
	public List<Token> parse(String expr){
		List<Token> tokens = new ArrayList<>();
		int i = 0;
		while(i < expr.length()){
			char c = expr.charAt(i);
			if(isOperator(c)){
				Token token = new Token(Token.OPERATOR, String.valueOf(c));
				tokens.add(token);
				i++;
			}else if(Character.isDigit(c)){
				int nextOperatorIndex = indexOfNextOperator(i, expr);
				String value = expr.substring(i, nextOperatorIndex);
				Token token = new Token(Token.NUMBER, value);
				tokens.add(token);
				i = nextOperatorIndex;
			}else{
				System.out.println("char :[" + c + "] is not number or operator,ignore");
				i++;
			}
		}
		return tokens;
	}
	
	public int indexOfNextOperator(int i,String expr){
		while(Character.isDigit(expr.charAt(i))){
			i++;
			if(i == expr.length()){
				break;
			}
		}
		return i;
	}
	
	public boolean isOperator(char c){
		String s = String.valueOf(c);
		return Token.OPERATORS.contains(s);
	}
}
