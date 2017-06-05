package basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

/*	public float evaluate() {
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(this.expr);
		Stack<Token> opStack = new Stack<>();
		Stack<Float> numStack = new Stack<>();

		for(int i=tokens.size()-1; i>=0; i--){
			Token token = tokens.get(i);
			if(token.isNumber()){
				numStack.push(new Float(token.getIntValue()));
			}
			if(token.isOperator()){
				Float f1 = numStack.pop();
				Float f2 = numStack.pop();
				Float result = calculate(token.toString(),f1,f2);
				numStack.push(result);
			}
		}

		while(!opStack.isEmpty()){
			Token token = opStack.pop();
			Float f1 = numStack.pop();
			Float f2 = numStack.pop();
			Float result = calculate(token.toString(),f1,f2);
			numStack.push(result);
		}
		System.out.println(numStack.peek());
		return numStack.pop().floatValue();
	}*/


	public float evaluate() {
		TokenParser parser = new TokenParser();
		List<Token> tokens = parser.parse(this.expr);

		Stack<Token> exprStack = new Stack<>();
		Stack<Float> numStack = new Stack<>();
		for(Token token : tokens){
			exprStack.push(token);
		}

		while(!exprStack.isEmpty()){
			Token t = exprStack.pop();
			if(t.isNumber()){
				numStack.push(new Float(t.getIntValue()));
			}else{
				Float f1 = numStack.pop();
				Float f2 = numStack.pop();
				numStack.push(calculate(t.toString(),f1,f2));

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
