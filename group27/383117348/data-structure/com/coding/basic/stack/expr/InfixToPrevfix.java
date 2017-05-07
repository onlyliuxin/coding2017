package com.coding.basic.stack.expr;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.coding.basic.stack.Stack;

public class InfixToPrevfix {

	public static List<Token> convert(String expr) {
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);

		return PrevOrderExpr(tokens);
	}

	public static List<Token> PrevOrderExpr(List<Token> tokens) {
		List<Token> result = new ArrayList<Token>();
		Stack operator = new Stack();
		Stack num = new Stack();
		for (int i = tokens.size() - 1; i >= 0; i--) {
			if (tokens.get(i).isNumber()) {
				num.push(tokens.get(i));
			}
			if (tokens.get(i).isOperator()) {
				Token token = null;
				if (!operator.isEmpty()) {
					token = (Token) operator.peek();
				}
				while (!operator.isEmpty() && token.hasHigherPriority(tokens.get(i))) {
					num.push((Token) operator.pop());
				}
				operator.push(tokens.get(i));
			}
		}
		while(!operator.isEmpty()){
			num.push(operator.pop());
		}
		while (!num.isEmpty()) {
			result.add((Token) num.pop());
		}
		

		return result;
	}
	
	
	public static void main(String[] args) {
		List<Token> list = InfixToPrevfix.convert("300*20+12*5-20/4");
		Assert.assertEquals("[+, *, 300, 20, -, *, 12, 5, /, 20, 4]", list.toString());
	}
}
