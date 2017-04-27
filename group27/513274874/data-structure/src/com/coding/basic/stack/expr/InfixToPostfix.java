package com.coding.basic.stack.expr;

import com.coding.basic.stack.Stack;

import java.util.ArrayList;
import java.util.List;

public class InfixToPostfix {
	private Stack operStack = new Stack();

	public List<Token> convert(String expr) {

		List<Token> infixList = TokenParser.parse(expr);
		List<Token> postfixList = new ArrayList<>();
		int i = 0;
		while(i < infixList.size()){
			Token token = infixList.get(i);
			if(token.isNumber()){
				//operand
				postfixList.add(token);

			}else{
				//operator
				if(operStack.isEmpty()) {
					operStack.push(token);
					i++;
					continue;
				}

				Token topToken = (Token)operStack.pop();

				if(token.hasHigherPriority(topToken)){
					//the operator has higher priority than the arg operator
					postfixList.add(infixList.get(++i));
					postfixList.add(token);
					operStack.push(topToken);

				}else{
					postfixList.add(topToken);
					operStack.push(token);
				}

			}
			i++;
		}
		//empty operStack
		while(!operStack.isEmpty()){
			postfixList.add((Token)operStack.pop());
		}
		for (Token token :postfixList){
			System.out.print(token.value);
		}
		System.out.println();
		return postfixList;
	}

}
