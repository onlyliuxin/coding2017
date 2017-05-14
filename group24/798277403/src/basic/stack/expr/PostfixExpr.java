package basic.stack.expr;

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

		Stack<Token> opStack = new Stack<>();
		Stack<Float> numStack = new Stack<>();
		for(Token token : tokens){
			if(token.isNumber()){
				numStack.push(new Float(token.getIntValue()));
			}
			if(token.isOperator()){
				if(numStack.size()>=2){
					Float f2 = numStack.pop();
					Float f1 = numStack.pop();
					Float result = calculate(token.toString(), f1,f2);
					numStack.push(result);
				}else{
					opStack.push(token);
				}
			}
		}

		while(!opStack.isEmpty()){
			Token token = opStack.pop();
			Float f2 = numStack.pop();
			Float f1 = numStack.pop();
			Float result = calculate(token.toString(), f1,f2);
			numStack.push(result);
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
