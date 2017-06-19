package code07;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {
String expr = null;
	
	public PostfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {

		Stack<Float> numStack = new Stack<Float>();
		List<Token> tokens = TokenParser.parseNoInfix(this.expr);

		for(Token token : tokens){
			if(token.isNumber()){
				numStack.push(new Float(token.getIntValue()));
			} else{
				Float f2 = numStack.pop();
				Float f1 = numStack.pop();
				numStack.push(calc(f1,f2,token.getStringValue()));
			}
		}

		return numStack.pop().floatValue();
	}

	//如下类似排比句的代码写法就是卫语句
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
