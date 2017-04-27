package test07.expr;

import java.util.List;
import java.util.Stack;

import test05.stack.StackUtil;

public class PrefixExpr {
	String expr = null;

	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(this.expr);
		Stack<Float> numStack = new Stack<>();
		
		for (int i = tokens.size()-1; i >=0; i--) {
			Token token=tokens.get(i);
			if (token.isOperator()){
				Float f2=numStack.pop();
				Float f1=numStack.pop();
				numStack.push(calculate(token.toString(), f2, f1));
			} 
			if(token.isNumber()){
				numStack.push(new Float(token.getIntValue()));
			}
		}		
		return numStack.pop().floatValue();
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
