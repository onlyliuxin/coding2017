package FixEXpr;

import java.util.List;
import java.util.Stack;

public class PostfixExpr {

	private String expr;
	public PostfixExpr(String expr)
	{
		this.expr=expr;
	}
	//6 5 2 3 + 8 * + 3 + *
	public float evaluate()
	{
		TokenParser parser=new TokenParser();
		List<Token> tokens=parser.parse(expr);
		Stack<Float> numStack=new Stack<>();
		for(Token token:tokens)
		{
			if(token.isNumber())
			{
				numStack.push(new Float(token.getIntValue()));
			}
			else{
				Float f2=numStack.pop();
				Float f1=numStack.pop();
				numStack.push(calculate(f1,f2,token.toString()));
			}
		}
		return numStack.pop().floatValue();
	}
	private Float calculate(Float f1, Float f2, String op) {
		if(op.equals("+"))
		{
			return f1+f2;
		}
		if(op.equals("-"))
		{
			return f1-f2;
		}
		if(op.equals("*"))
		{
			return f1*f2;
		}
		if(op.equals("/"))
		{
			return f1/f2;
		}
		throw new RuntimeException(op+"is not supported!");
	}
}
