package FixEXpr;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中缀表达式转前缀表达式
 * @author 阿杰
 *
 */
public class InfixToPrefix {

	public static List<Token> convert(String expr)
	{
		List<Token> inFixTokens=new TokenParser().parse(expr);
		//先将每一个token放到一个栈中
		Stack<Token> inFix=new Stack<>();
		for(Token token:inFixTokens)
		{
			inFix.push(token);
		}
		List<Token> preFixTokens=new ArrayList<>();
		Stack<Token> preFix=new Stack<>();
		Stack<Token> opStack=new Stack<>();
		while(!inFix.isEmpty())
		{
			Token token=inFix.pop();
			if(token.isNumber())
			{
				preFix.push(token);
			}
			else{
				while(!opStack.isEmpty()&&!token.hasHigherPriority(opStack.peek()))
				{
					preFix.push(opStack.pop());
				}
				opStack.push(token);
			}
		}
		while(!opStack.isEmpty())
		{
			preFix.push(opStack.pop());
		}
		while(!preFix.isEmpty())
		{
			preFixTokens.add(preFix.pop());
		}
		return preFixTokens;
	}
}
