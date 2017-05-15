package com.dudy.learn01.data_structure.queue.expr;

import java.util.List;
import java.util.Stack;

public class InfixExpr {
	String expr = null;
	Stack<Token> number = new Stack<Token>();
	Stack<Token> opt = new Stack<Token>();

	public InfixExpr(String expr) {

		this.expr = expr;
	}

	public float evaluate() {

		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);


		for (Token token: tokens){
		    if (token.isOperator()){
		        if (opt.isEmpty()){
		            opt.push(token);
                } else {
		            while (!opt.isEmpty() &&
                            !token.hasHigherPriority(opt.peek())){
                        Token prevOperator = opt.pop();
                        calculator(number.pop(),number.pop(),prevOperator.value);
                    }
                    opt.push(token);

                }
            }

            if (token.isNumber()){
		        number.push(token);
            }
        }

        while (!opt.isEmpty()){
		    calculator(number.pop(), number.pop(),opt.pop().value);
        }



//		for (int i = 0; i < tokens.size(); i++) {
//			Token token = tokens.get(i);
//			if (token.isNumber()) {
//				number.push(token);
//			}
//
//			if (token.isOperator()) {
//				if (opt.isEmpty()) {
//					opt.push(token); continue;
//				}
//
//				if (!opt.isEmpty() && !token.hasHigherPriority(opt.peek())) {
//					calculator(tokens.get(++i), number.pop(), token.value);
//
//				} else {
//					opt.push(token);
//				}
//			}
//		}
//
//		while(!opt.empty()){
//
//            calculator(number.pop(), number.pop(), opt.pop().value);
//		}

		return Float.parseFloat(number.pop().value);
	}

	/**
	 * 
	 * @param token 第二个操作数
	 * @param pop   第一个操作数
	 * @param op	运算符
	 */
	private void calculator(Token token, Token pop, String op) {
		Integer number1 = Integer.parseInt(pop.value);
		Integer number2 = Integer.parseInt(token.value);
		Integer value = null;
		if ("*".equals(op)) {
		 	value = number1 * number2;
		}
		if ("/".equals(op)) {
		 	value = number1 / number2;
		}
		if ("-".equals(op)) {
		 	value = number1 - number2;
		}
		if ("+".equals(op)) {
		 	value = number1 + number2;
		}
		
		number.push(new Token(2,value+""));
	}

}