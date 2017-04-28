package task0416.coding.basic.stuck.expr;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.MediaSize.ISO;

public class TokenParser {
		
	public List<Token> parse(String expr){
		List<Token> array = new ArrayList<>();
		
		int i = 0;
		while(i<expr.length()){
			char c = expr.charAt(i);
			if(isOperator(c)){
				Token t = new Token(Token.OPERATOR,String.valueOf(c));
				array.add(t);
				i++;
			}else if(Character.isDigit(c)){
				int nextOperatorIndex = nextOperatorIndex(i,expr);				
				String value = expr.substring(i, nextOperatorIndex);
				Token t = new Token(Token.NUMBER,value);
				array.add(t);
				i = nextOperatorIndex;
			}else {
				System.out.println("char:["+c+"] is not number or operator,ignore");
				i++;
			}
		}
		return array;
	}
	private int nextOperatorIndex(int i, String expr2) {
		
		while(Character.isDigit(expr2.charAt(i))){
			i++;
			if(i == expr2.length()){
				break;
			}
		}
		return i;
	}

	boolean isOperator(char ch){
		String s = String.valueOf(ch);
		boolean result = "+".equals(s)||"-".equals(s)||"*".equals(s)||"/".equals(s);
		return result;	
	}
}
