package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class PrefixExpr {
	String expr = null;
	
	public PrefixExpr(String expr) {
		this.expr = expr;
	}

	public float evaluate() {
		
		TokenParser tokenParser =new TokenParser();
		List<Token> tokens=tokenParser.parse(expr);
		Stack<Float> numStack =new Stack<>();
		for (int i = tokens.size()-1; i >=0; i--) {
			Token token=tokens.get(i);
			if (token.isOperator()) {
				float op1=(float)numStack.pop();
				float op2=(float)numStack.pop();
				char op =token.toString().charAt(0);
				numStack.push(caculate(op1, op2, op));
			}
			else {
				numStack.push((float)token.getIntValue());
			}
			
		}
		return numStack.pop();
		
	}
	private float caculate(float op1,float op2,char op){
		

		float tmpResult = 0;
		switch(op){  
        case '+':  
            tmpResult = op1 + op2;            
            break;  
        case '-':  
            tmpResult = op1 - op2;  
            break;  
        case '*':  
            tmpResult = op1 * op2;  
            break;  
        case '/':  
            tmpResult = op1 / op2;  
            break;  
    }  
		return tmpResult;
	}
	
}
