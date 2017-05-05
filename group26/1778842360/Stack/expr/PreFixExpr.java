package FixEXpr;

import java.util.List;
import java.util.Stack;

/**
 * 前缀表达式的计算：运算符在前，操作数在后
 * 对于前缀表达式的计算，是从后往前算的
 * @author 阿杰
 *
 */
public class PreFixExpr {
    
	String expr=null;
	
	public PreFixExpr(String expr)
	{
		this.expr=expr;
	}
	
	public float evaluate()
	{
		TokenParser parser=new TokenParser();
		List<Token> tokens=parser.parse(expr);
		
		Stack<Token> exprStack=new Stack<>();
		Stack<Float> numStack=new Stack<>();
		
		for(Token token:tokens)
		{
			exprStack.push(token);
		}
		while(!exprStack.isEmpty())
		{
			Token t=exprStack.pop();
			if(t.isOperator())
			{
				Float f1=numStack.pop();
				Float f2=numStack.pop();
				Float result=calculate(t.toString(),f1,f2);
				numStack.push(result);
			}
			if(t.isNumber()){
				numStack.push(new Float(t.getIntValue()));
			}
		}
		return numStack.pop().floatValue();
	}

	private Float calculate(String op, Float f1, Float f2) {
		// TODO Auto-generated method stub
		
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
