package task0416.coding.basic.stuck.expr;

import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.MediaSize.ISO;

public class TokenParser {
	private String expr;
	
	TokenParser(String expr){
		this.expr = expr;
	}
	
	public List<Token> getParserList(){
		List<Token> array = new ArrayList<>();
		char[] chs = expr.toCharArray();
		int i = 0;
		while(i<chs.length){
			char c = chs[i];
			if(isOperator(c)){
				Token t = new Token(Token.OPERATOR,String.valueOf(c));
				if("+".equals(String.valueOf(c))||"-".equals(String.valueOf(c))){
					t.setLevel(Token.LEVEL_ADD_SUB);
				}else{
					t.setLevel(Token.LEVEL_MUL_DIV);
				}
				array.add(t);
				i++;
			}else if(Character.isDigit(c)){
				int nextOperatorIndex = nextOperatorIndex(i,expr);				
				String value = expr.substring(i, nextOperatorIndex);
				Token t = new Token(Token.NUMBER,value);
				array.add(t);
				i = nextOperatorIndex;
			}
		}
		return array;
	}
	private int nextOperatorIndex(int i, String expr2) {
		char[] chs = expr2.toCharArray();
		for (int j = i; j < chs.length; j++) {
			if(isOperator(chs[j])){
				return j;
			}
		}
		return chs.length;
	}

	boolean isOperator(char ch){
		String s = String.valueOf(ch);
		boolean result = "+".equals(s)||"-".equals(s)||"*".equals(s)||"/".equals(s);
		return result;	
	}
}
