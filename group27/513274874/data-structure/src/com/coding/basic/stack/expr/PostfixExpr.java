package com.coding.basic.stack.expr;

import com.coding.basic.stack.Stack;

import java.util.List;

public class PostfixExpr {
String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		List<Token> postfixExpr = TokenParser.parse(expr);

		Stack calc = new Stack();
		for(Token token :postfixExpr){
			if(token.isNumber()){
				//push number into stack until operator
				calc.push(token.getIntValue());
			}else {
				//operator : pop two numbers to calculate and then push the result into stack
				int numberA = (int)calc.pop();
				int numberB = (int)calc.pop();

				switch (token.value){
					case "+":
						calc.push(numberB + numberA );
						break;
					case "-":
						calc.push(numberB - numberA);
						break;
					case "*":
						calc.push(numberB * numberA);
						break;
					case "/":
						calc.push(numberB / numberA);
						break;

					default:
						throw new RuntimeException(token.value + " is not a operator !");
				}
			}
		}

		//the result is the only value in the stack
		float result = Float.parseFloat(calc.pop() + "");
		return result;
	}
}
