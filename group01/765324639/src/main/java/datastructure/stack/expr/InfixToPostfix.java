package datastructure.stack.expr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {
		
	    TokenParser tokenParser = new TokenParser(expr);
	    List<Token> tokenList = tokenParser.parse();
	    
	    Stack<Token> operStack = new Stack<>();
	    Stack<Token> tempStack = new Stack<>();
	    for (int i = 0; i < tokenList.size(); i++) {
	        Token token = tokenList.get(i);
	        if (token.isNumber()) {
	            tempStack.push(token);
	        } else if (token.isOperator()) {
	            if (operStack.isEmpty() || "(".equals(operStack.peek().getValue())) {
	                operStack.push(token);
	            } else if (")".equals(token.getValue())) {
	                while (!"(".equals(operStack.peek().getValue())) {
	                    tempStack.push(operStack.pop());
	                }
	                operStack.pop(); // 去掉左括号
	            } else if (token.comparePriority(operStack.peek()) > 0){
	                operStack.push(token);
	            } else {
	                tempStack.push(operStack.pop());
	                i--;
	            }
	        }
	    }
	    
	    while (!operStack.empty()) {
	        tempStack.push(operStack.pop());
	    }
	    
	    List<Token> list = new ArrayList<>();
	    while (!tempStack.empty()) {
	        list.add(tempStack.pop());
	    }
	    Collections.reverse(list);
		return list;
	}
	
	

}
