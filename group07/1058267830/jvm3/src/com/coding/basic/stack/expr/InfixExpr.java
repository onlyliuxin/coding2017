package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;


public class InfixExpr {
	String expr = null;
	TokenParser tokenParser = new TokenParser();
	
	public InfixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		List<Token> list = tokenParser.parse(expr);
		if(list == null)
			throw new RuntimeException("input error...");
		Stack<Integer> stackNumber = new Stack<Integer>();
		Stack<Token> stackOperate = new Stack<Token>();
		for(int i=list.size()-1; i>=0; i--){
			if(list.get(i).isNumber()){
				stackNumber.push(list.get(i).getIntValue());
			}else{
				stackOperate.push(list.get(i));
			}
		}
		
		while(stackOperate != null) {
			if(stackOperate.size() == 1){
				int a1 = stackNumber.pop();
				int a2 = stackNumber.pop();
				Token tmp = stackOperate.pop();
				return operate(a1, a2, tmp);
			}
			Token tmp = stackOperate.pop();
			if(tmp.hasHigherPriority(stackOperate.peek())){
				int a1 = stackNumber.pop();
				int a2 = stackNumber.pop();
				int res = operate(a1,a2,tmp);
				stackNumber.push(res);
			}else{
				int a1 = stackNumber.pop();
				int a2 = stackNumber.pop();
				int a3 = stackNumber.pop();
				Token tmp2 = stackOperate.pop();
				int res = operate(a2, a3, tmp2);
				stackNumber.push(res);
				stackNumber.push(a1);
				stackOperate.push(tmp);
			}
			
		}
		
		return stackNumber.peek();
		
	}

	private int operate(int a1, int a2, Token token) {
		String s = token.toString();
		if(s.equals("+"))
			return a1 + a2;
		else if(s.equals("-"))
			return a1 - a2;
		else if(s.equals("*"))
			return a1 * a2;
		else if(s.equals("/"))
			return a1/a2;
		else
			throw new RuntimeException("解析符号错误...");
	}
	
	
}
