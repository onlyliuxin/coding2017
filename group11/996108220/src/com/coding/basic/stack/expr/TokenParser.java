package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TokenParser {
	
	
	public  List<Token> parse(String expr) {
		List<Token> tokens = new ArrayList<>();
		
		
		 StringTokenizer st = new StringTokenizer(expr, "\\+|\\-|\\*|\\/|\\ ", true);  

		  while(st.hasMoreElements()){  
			  String  value=st.nextToken();
			  //可能有空格
			  if (isOperator(value)) {
				tokens.add(new Token(Token.OPERATOR, value));
			  }
			  else if (isNumber(value)) {
				tokens.add(new Token(Token.NUMBER, value));
			  }
		  }
		
		return tokens;
	}

	

	private  boolean isOperator(String value) {
		
		return value.length()==1&&Token.OPERATORS.contains(value);
	}
	private  boolean isNumber(String value) {
		
		return value.matches("[0-9]+");
	}
	
}
