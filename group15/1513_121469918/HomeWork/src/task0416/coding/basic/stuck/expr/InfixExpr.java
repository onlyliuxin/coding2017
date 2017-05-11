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
		
		TokenParser tp = new TokenParser(expr);
		List<Token> array = tp.getParserList();
		
		for (int i = 0; i < array.size(); i++) {
			Token t = array.get(i);
			String type = t.getType();
			
			if(type.equals(Token.NUMBER)){
				nStack.push(Float.valueOf(t.getValue()));
			}else if(type.equals(Token.OPERATOR)){
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
		return null;	
	}
	

	
	
}
