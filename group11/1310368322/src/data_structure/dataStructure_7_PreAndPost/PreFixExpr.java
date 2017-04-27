package dataStructure_7_PreAndPost;

import java.util.List;
import java.util.Stack;

public class PreFixExpr {
	
	String expr = null;
	 
	public PreFixExpr(String expr){
		this.expr = expr;
	}
	
	public float evaluate(){
		
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(expr);
		Stack<Token> operatorStack = new Stack();
		Stack<Float> operandStack = new Stack();
		
		for(int i = tokens.size()-1; i >= 0; i--){
			if(tokens.get(i).isNumber()){
				operandStack.push(new Float(tokens.get(i).getIntValue()));
			}else if(tokens.get(i).isOperator()){
				checkOperandStack(operandStack.size());
				Float operand = operandStack.pop();
				Float operanded = operandStack.pop();
				Float result = calculate(tokens.get(i).toString(),operand, operanded);
				operandStack.push(result);
			}
		}
		
		return operandStack.pop();
	}

	private boolean checkOperandStack(int size) {
		if(size < 2){
			throw new RuntimeException("操作数栈中的元素太少");
		}
		return true;
	}
	
	
	private Float calculate(String op, Float f1, Float f2){
		if(op.equals("+")){
			return f1+f2;
		}
		if(op.equals("-")){
			return f1-f2;
		}
		if(op.equals("*")){
			return f1*f2;
		}
		if(op.equals("/")){
			return f1/f2;
		}
		throw new RuntimeException(op + " is not supported");
	}
	
}
