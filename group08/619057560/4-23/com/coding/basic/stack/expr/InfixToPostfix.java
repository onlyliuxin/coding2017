package com.coding.basic.stack.expr;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class InfixToPostfix {

	public static List<Token> convert(String expr) throws InfixExprException {

		List<Token> resultTokens = new LinkedList<>();
		
		Iterator<Token> iter = new TokenParser().parse(expr).iterator();
		int markIndex = 0;
		while (true) {
			if (!iter.hasNext()) {
				throw new InfixExprException();
			}
			Token number = iter.next();
			if (number.isOperator()) {
				throw new InfixExprException();
			}
			if (!iter.hasNext()) {
				resultTokens.add(markIndex, number);
				break;
			}
			Token op = iter.next();
			if (op.isNumber()) {
				throw new InfixExprException();
			}
			
			if (!resultTokens.isEmpty() && op.hasHigherPriority(resultTokens.get(markIndex))) {
				resultTokens.add(markIndex, number);
				markIndex++;
			} else {
				resultTokens.add(markIndex, number);
				markIndex = resultTokens.size();
			}
			resultTokens.add(markIndex, op);
		}
		
		System.out.println(resultTokens.toString());
		return resultTokens;
	}
	
}
