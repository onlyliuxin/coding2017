package dataStructure_7_PreAndPost;

import java.util.List;
import java.util.Stack;

public class PostFixExpr {
	
	String expr = null;
	
	public PostFixExpr(String expr){
		this.expr = expr;
	}
	
	public float evaluate(){
		//Stack<Token> operatorStack = new Stack<Token>();
		Stack<Float> operandStack = new Stack<Float>();
		TokenParser parser = new TokenParser();
		List<Token> list = parser.parse(expr);
		for (Token token : list) {
			if(token.isNumber()){
				Float operand = Float.parseFloat(token.toString());
				operandStack.push(operand);
			}else if(token.isOperator()){
				Float operand = operandStack.pop();
				Float operanded = operandStack.pop();
				operandStack.push(calculate(token.toString(), operanded, operand));
			}
		}
		return operandStack.pop();
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
