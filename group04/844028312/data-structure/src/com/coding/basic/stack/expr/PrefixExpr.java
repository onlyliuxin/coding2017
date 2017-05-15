package com.coding.basic.stack.expr;

import java.util.List;

import com.coding.basic.Stack;

public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		TokenParser tokenParser=new TokenParser();
		List<Token> tokens=tokenParser.parse(expr);
		Stack stack=new Stack();
		for(Token token:tokens){
			if(token.isNumber()){
				float result = 0;
				if(!(stack.peek().equals("-")||stack.peek().equals("*")||stack.peek().equals("/")||stack.peek().equals("+"))){
					float one = new Float( (float)stack.pop());
					float two =token.getIntValue();
					String ope=(String) stack.pop();
					if(ope.equals("-")){
						result=one-two;
					}
					else if(ope.equals("*")){
						result=two*one;
					}
					else if(ope.equals("/")){
						result=one/two;
					}
					else if(ope.equals("+")){
						result=two+one;
					}
					
					while(!("-".equals(stack.peek())||"*".equals(stack.peek())||"/".equals(stack.peek())||"+".equals(stack.peek()))&&!stack.isEmpty()){
						 one =new Float( (float)stack.pop());
						
						ope=(String) stack.pop();
							if(ope.equals("-")){
								result=one-result;
							}
							else if(ope.equals("*")){
								result=result*one;
							}
							else if(ope.equals("/")){
								result=one/result;
							}
							else if(ope.equals("+")){
								result=result+one;
							}
					
					}	
					stack.push(result);
				}
				else
				 stack.push(new Float((float)token.getIntValue()));
				
			}
			else if(token.isOperator()){

				stack.push(token.toString());
			}
		}
		while(stack.size()>1){
			float one = new Float( (float)stack.pop());
			float two = new Float( (float)stack.pop());
			float result = 0;
			String ope=(String) stack.pop();
			if(ope.equals("-")){
				result=two-one;
			}
			else if(ope.equals("*")){
				result=two*one;
			}
			else if(ope.equals("/")){
				result=two/one;
			}
			else if(ope.equals("+")){
				result=two+one;
			}
			stack.push(result);
			
		}
		return  (float) stack.pop() ;
	}
	
	
}
