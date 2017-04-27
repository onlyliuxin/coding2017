package stack.expr;

import java.util.List;
import java.util.Stack;

public class InfixToPostfix {
	
	public static List<Token> convert(String expr) {

		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		List<Token> pTokens = new Stack<>();
		Stack<Token> opStack = new Stack<>();
		
		for (Token token : tokens) {
			if (token.isNumber()) {
				pTokens.add(token);
			} else {
				if (token.isLeftSquare()) {
					opStack.push(token);
				} else if (token.isRightSquare()) {
					while (!opStack.peek().isLeftSquare()) {
						pTokens.add(opStack.pop());
					}
					opStack.pop();
				} else {
					if (opStack.isEmpty() || token.hasHigherPriority(opStack.peek())) {
						opStack.add(token);
					} else {
						while (!opStack.isEmpty() && !token.hasHigherPriority(opStack.peek())) {
							pTokens.add(opStack.pop());
						}
						opStack.add(token);
					}
				}
			}
		}
		
		while(!opStack.isEmpty()) {
			pTokens.add(opStack.pop());
		}
		
		System.out.println(pTokens);
		return pTokens;
	}

	public static void main(String[] args) {
		convert("10-2*3+50");
	}
	
}
