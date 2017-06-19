package code07;

import java.util.List;
import java.util.Stack;

public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		Stack<Token> exprStack = new Stack<Token>();
		Stack<Float> numberStack = new Stack<Float>();

		List<Token> tokens = TokenParser.parseNoInfix(this.expr);

		for(Token t: tokens){
			 exprStack.push(t);
		}

		while (!exprStack.isEmpty()){
			Token t = exprStack.pop();
			if(Token.OPERATOR == t.getType()){
				float a = numberStack.pop();
				float b = numberStack.pop();
				float result = calc(a,b,t.getStringValue());
				numberStack.push(result);
			}else if(Token.NUMBER == t.getType()){
				numberStack.push(Float.parseFloat(t.getStringValue()));
			}else {
				System.out.println("char :["+t.getStringValue()+"] is not number or operator,ignore");
			}
		}

		return numberStack.pop().floatValue();
	}

	private Float calc(Float f1, Float f2, String op){
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
