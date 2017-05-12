package dataStructure_7_PreAndPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr){
		
		List<Token> result = new ArrayList<Token>();
		Stack<Token> operatorStack = new Stack<Token>();
		TokenParser parser = new TokenParser();
		List<Token> list = parser.parse(expr);

		for (Token token : list) {
			
			if(token.isNumber()){
				result.add(token);
			}else if(token.isOperator()){
				
				if(operatorStack.isEmpty()){
					operatorStack.push(token);
				}else{
					
					Token topOperator = operatorStack.pop();
					
					if(!token.hasHigherPriority(topOperator)){
						list.add(topOperator);
						while(true){
							if(!operatorStack.isEmpty()){
								Token top = operatorStack.pop();
								
								if(!token.hasHigherPriority(top) && !operatorStack.isEmpty()){
									list.add(top);
								}else{
									break;
								}
							}	
							
						}
						operatorStack.push(token);
					}else{
						operatorStack.push(topOperator);
						operatorStack.push(token);
					}
					
				}
				
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
		List<Token> result = convert("3+2*5+6");
		for (Token token : result) {
			System.out.println(token);
		}
	}
}
