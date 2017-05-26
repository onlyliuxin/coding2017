package expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		List<Token> inList = TokenParser.parse(expr);
		List<Token> postList = new ArrayList<Token>();
		Stack<Token> operatorStack = new Stack<Token>();
		for(int i = 0; i < inList.size(); i++){
			Token token = inList.get(i);
			if(token.isOperator()){
				while(!operatorStack.isEmpty() && !token.hasHigherPriority(operatorStack.peek())){
					postList.add(operatorStack.pop());
				}
				operatorStack.add(token);

			}else{
				postList.add(token);
			}
		}
		
		while(!operatorStack.isEmpty()){
			postList.add(operatorStack.pop());
		}
		
		
		return postList;
	}
	
	public static String toString(List<Token> tokenList){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < tokenList.size(); i++){
			sb.append(tokenList.get(i).getValue());
			if(i < tokenList.size()-1){
				sb.append(" ");
			}
		}
		return sb.toString();
	}
	
	

}
