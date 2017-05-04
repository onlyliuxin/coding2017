package com.github.miniyk2012.coding2017.basic.stack.expr;

import java.util.List;
import java.util.Stack;


/**
 https://en.wikipedia.org/wiki/Reverse_Polish_notation

 While there are input tokens left
     Read the next token from input.
        If the token is a value
            Push it onto the stack.
        Otherwise, the token is an operator (operator here includes both operators and functions).
            It is already known that the operator takes n arguments.
            If there are fewer than n values on the stack
                (Error) The user has not input sufficient values in the expression.
            Else, Pop the top n values from the stack.
            Evaluate the operator, with the values as arguments.
            Push the returned results, if any, back onto the stack.
     If there is only one value in the stack
        That value is the result of the calculation.
     Otherwise, there are more values in the stack
        (Error) The user input has too many values.
 */
public class PostfixExpr {
String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
        TokenParser parser = new TokenParser();
        List<Token> tokens  = parser.parse(expr);
        Stack<Token> operandStack = new Stack<>();
        for (Token token: tokens) {
            if (token.isNumber()) {
                operandStack.push(token);
            } else {
                Token operand1 = operandStack.pop();
                Token operand2 = operandStack.pop();
                Token result = ExprUtil.evalute(operand2, token, operand1);
                operandStack.push(result);
            }
        }
		return operandStack.pop().getFloatValue();
	}
	
	
}
