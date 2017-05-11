package FixEXpr;

import java.util.List;
import java.util.Stack;

public class InToPreExpr {
	
	public String convert(String expr)
	{
	    TokenParser parser=new TokenParser();
	    List<Token> tokens=parser.parse(expr);
	    Stack<Token> tStack=new Stack<>();
	    for(Token token:tokens)
	    {
	    	tStack.push(token);
	    }
	    Stack<Token> opStack=new Stack<>();
	    Stack<Token> numStack=new Stack<>();
	    while(!tStack.isEmpty())
	    {
	    	Token t=tStack.pop();
	    	if(t.isOperator())
	    	{
	    		if(opStack.isEmpty())
	    		{
	    			opStack.push(t);
	    		}
	    		else if(!opStack.isEmpty()&&!t.hasHigherPriority(opStack.peek())){
	    			
	    			while(!opStack.isEmpty()&&!t.hasHigherPriority(opStack.peek()))
	    			{
	    				numStack.push(opStack.pop());
	    			}
	    			opStack.push(t);
	    		}
	    		else{
	    			opStack.push(t);
	    		}
	    		
	    	}
	    	else{
	    		numStack.push(t);
	    	}
	    }
	    while(!opStack.isEmpty())
	    {
	    	numStack.push(opStack.pop());
	    }
	    StringBuilder sb=new StringBuilder();
	    while(!numStack.isEmpty())
	    {
	    	sb.append(numStack.pop());
	    }
		return sb.toString();
	}
}
