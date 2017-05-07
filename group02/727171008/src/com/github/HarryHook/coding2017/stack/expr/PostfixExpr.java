package com.github.HarryHook.coding2017.stack.expr;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {
    String expr = null;

    public PostfixExpr(String expr) {
	this.expr = expr;
    }

    public float evaluate() {

	TokenParser parser = new TokenParser();
	List<Token> tokens = parser.parse(this.expr);

	Stack<Float> numStack = new Stack<>();
	for (Token token : tokens) {

	    if (token.isNumber()) {
		numStack.push(new Float(token.getIntValue()));
	    } else {
		Float f2 = numStack.pop();
		Float f1 = numStack.pop();
		numStack.push(calculate(token.toString(), f1, f2));
	    }
	}
	return numStack.pop().floatValue();
    }

    // 注意，此时计算的顺序和前序的次序相反
    private Float calculate(String op, Float f1, Float f2) {
	if (op.equals("+")) {
	    return f1 + f2;
	}
	if (op.equals("-")) {
	    return f1 - f2;
	}
	if (op.equals("*")) {
	    return f1 * f2;
	}
	if (op.equals("/")) {
	    return f1 / f2;
	}
	throw new RuntimeException(op + " is not supported");
    }

    public static void main(String[] args) {
	PostfixExpr expr = new PostfixExpr("9 3 1-3*+ 10 2/+");
	System.out.println("The result of the expression: " + expr.evaluate());
    }

}