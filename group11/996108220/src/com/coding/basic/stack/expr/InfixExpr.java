package com.coding.basic.stack.expr;


import java.util.List;
import java.util.Stack;

import com.sun.org.apache.xpath.internal.compiler.OpCodes;



public class InfixExpr {
	String expr=null;
	public InfixExpr(String expr) {
		 this.expr=expr;	  
	}
	//5-2+3 6/2*3 5-2+3*4 2+3-5
	public float evaluate() {
		TokenParser tokenParser=new TokenParser();
		List<Token> tokens= tokenParser.parse(expr);
		Stack<Token> opStack=new Stack<>();
		Stack<Float> numStack=new Stack<>();
		for (Token token : tokens) {
			if (token.isNumber()) {	
				numStack.push((float)(token.getIntValue()));
			}
			else {
				while (!opStack.isEmpty()&&!token.hasHigherPriority(opStack.peek())) {
					float op2=numStack.pop();
					float op1= numStack.pop();
				
					char op=opStack.pop().toString().charAt(0);
					numStack.add(caculate(op1,op2,op));
					
				}
				opStack.push(token);
			}
		}

		while (opStack.size()!=0) {
			
			float op2=numStack.pop();
			float op1= numStack.pop();
			char op=opStack.pop().toString().charAt(0);
			numStack.add(caculate(op1,op2,op));
		}
		return (float) numStack.pop();
	}
	public float caculate(float op1,float op2,char op){
		

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
