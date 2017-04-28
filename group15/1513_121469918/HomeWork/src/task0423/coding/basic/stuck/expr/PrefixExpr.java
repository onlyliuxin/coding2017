package task0423.coding.basic.stuck.expr;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import task0416.coding.basic.stuck.expr.Token;
import task0416.coding.basic.stuck.expr.TokenParser;

public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser tp = new TokenParser();
		List<Token> array = tp.parse(expr);
		Collections.reverse(array);
		
		Stack<Float> stack = new Stack<>();
		for(Token t : array){
			if(t.isNumber()){
				stack.push(Float.valueOf(t.getValue()));
			}
			if(t.isOperator()){
				stack.push(operation(stack,t.getValue()));
			}
		}
		
		return stack.peek();
	}
	
	private Float operation(Stack<Float> nStack,String operator) {
		Float num1 = nStack.pop();
		Float num2 = nStack.pop();
		
		switch(operator){
		case "+":
			return num2+num1;
		case "-":
			return num1-num2;
		case "*":
			return num2*num1;
		case "/":
			return num1/num2;	
		}
		throw new RuntimeException(operator + " is not supported");
	}
}
