package FixEXpr;

import java.util.List;
import java.util.Stack;

public class InFixExpr {
	
   String expr=null;
   public InFixExpr(String expr)
   {
	   this.expr=expr;
   }
   
   public float evaluate()
   {
	   TokenParser parser=new TokenParser();
	   List<Token> tokens=parser.parse(this.expr);
	   
	   
	   Stack<Token> opStack=new Stack<>();
	   Stack<Float> numStack=new Stack<>();
	   
	   for(Token token:tokens)
	   {
		   if(token.isOperator())
		   {
			   if(opStack.isEmpty())
			   {
				   opStack.push(token);
			   }else{
				   //当前运算符的优先级比运算符栈栈顶的优先级低，则进行计算
				   while(!opStack.isEmpty()&&!token.hasHigherPriority(opStack.peek()))
				   {
					   //弹出栈顶运算符
					   Token prevOperator=opStack.pop();
					   //弹出两个操作数
					   Float f2=numStack.pop();
					   Float f1=numStack.pop();
					   Float result=calculate(prevOperator.toString(),f1,f2);
					   numStack.push(result);
				   }
				   opStack.push(token);
			   }
		   }
		   if(token.isNumber())
		   {
			   numStack.push(new Float (token.getIntValue()));
		   }
	   }
	   
	   while(!opStack.isEmpty())
	   {
		    Token token = opStack.pop();
			Float f2 = numStack.pop();
			Float f1 = numStack.pop();
			numStack.push(calculate(token.toString(), f1,f2));
	   }
	   
	   
	   return numStack.pop().floatValue();
   }

	private Float calculate(String op, Float f1, Float f2) {
		// TODO Auto-generated method stub
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
		throw new RuntimeException(op+"is not supported");
	}
}
