package week07.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	public static List<Token> convert(String expr) {
		TokenParser tokenParser = new TokenParser();
		List<Token> tokens = tokenParser.parse(expr);
		List<Token> rsTokens = new ArrayList<>();
		Stack<Token> operatorStack = new Stack<>();
		for(int i=0 ; i < tokens.size(); i++){
			Token token = tokens.get(i);
			if(token.isNumber()){
				rsTokens.add(token);
				continue;
			}
			
			if(token.isOperator()){
				if(operatorStack.isEmpty()){
					operatorStack.push(token);
					continue;
				}
				
				Token curStack = operatorStack.peek();
				if(token.hasHigherPriority(curStack)){
					operatorStack.push(token);
				}else{
					while(!operatorStack.isEmpty() && (curStack = operatorStack.peek()).hasHigherPriority(token)){
						rsTokens.add(curStack);
						operatorStack.pop();
					}
					operatorStack.push(token);
				}
				
			}
		}
		
		while(!operatorStack.isEmpty()){
			rsTokens.add(operatorStack.pop());
		}
		return rsTokens;
	}
}
