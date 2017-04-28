package task0416.coding.basic.stuck.expr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InfixExpr {
	String expr = null;
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}
	/*
	 
	 */
	public float evaluate() {	
		Stack<Token> oStack = new Stack<>();
		Stack<Float> nStack = new Stack<>();
		
		TokenParser tp = new TokenParser();
		List<Token> array = tp.parse(expr);
		
		for (int i = 0; i < array.size(); i++) {
			Token t = array.get(i);
			
			if(t.isNumber()){
				nStack.push(Float.valueOf(t.getValue()));
			}else if(t.isOperator()){
				if(oStack.isEmpty()||t.getLevel()>oStack.peek().getLevel()){
					oStack.push(t);
				}else{
					nStack.push(operation(nStack,oStack));
					oStack.push(t);
				}
			}
		}
		while(!oStack.isEmpty()){
			nStack.push(operation(nStack,oStack));
		}
		return nStack.peek();
	}
	private Float operation(Stack<Float> nStack, Stack<Token> oStack) {
		Float num1 = nStack.pop();
		Float num2 = nStack.pop();
		String operator = oStack.pop().getValue();
		
		switch(operator){
		case "+":
			return num2+num1;
		case "-":
			return num2-num1;
		case "*":
			return num2*num1;
		case "/":
			return num2/num1;	
		}
		throw new RuntimeException(operator + " is not supported");
	}
	

	
	
}
