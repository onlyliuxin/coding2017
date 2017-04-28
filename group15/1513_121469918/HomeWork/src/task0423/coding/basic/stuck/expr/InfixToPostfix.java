package task0423.coding.basic.stuck.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import task0416.coding.basic.stuck.expr.Token;
import task0416.coding.basic.stuck.expr.TokenParser;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		
		Stack<Token> stack = new Stack<>();
		List<Token> result = new ArrayList<>();
		
		for(Token token: tokens){
			if(token.isNumber()){
				result.add(token);
			}
			if(token.isOperator()){
				if(stack.isEmpty() || token.getLevel()>stack.peek().getLevel()){
					stack.push(token);
				}else if (token.getLevel()<=stack.peek().getLevel()){
					result.add(stack.pop());
					stack.push(token);
				}
			}
		}
		while(!stack.isEmpty()){
			result.add(stack.pop());
		}
	    return result;
	}
	
	public static String convertToString (String expr){
		StringBuilder sb = new StringBuilder();
		List<Token> array = convert(expr);
		for(Token token : array){
			sb.append(token.getValue());
			sb.append(" ");
		}
		return sb.toString();
	}

}
