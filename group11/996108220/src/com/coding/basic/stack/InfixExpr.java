package com.coding.basic.stack;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class InfixExpr {
	ArrayList<String> list=new ArrayList<>();
	Stack numStack=new Stack();
	Stack operStack=new Stack();
	public InfixExpr(String s) {
		 StringTokenizer st = new StringTokenizer(s, "\\+|\\-|\\*|\\/", true);  

		  while(st.hasMoreElements()){  
			  list.add(st.nextToken());
		  }
	}
	//5-2+3 6/2*3 5-2+3*4 2+3-5
	public float evaluate() {

		for (int i = 0; i < list.size(); i++) {
			
			if (list.get(i).charAt(0)=='+') {
				if (operStack.size()==0) {
					operStack.push(list.get(i).charAt(0));
					continue;
				}
				if ((char)operStack.peek()=='-'||(char)operStack.peek()=='*'||(char)operStack.peek()=='/') {
					numStack.push(caculate());
				}
				operStack.push(list.get(i).charAt(0));
			}
			else if (list.get(i).charAt(0)=='-') {
				if (operStack.size()==0) {
					operStack.push(list.get(i).charAt(0));
					continue;
				}
				if ((char)operStack.peek()=='*'||(char)operStack.peek()=='/') {
					numStack.push(caculate());
				}
				operStack.push(list.get(i).charAt(0));
			}
			else if (list.get(i).charAt(0)=='*') {
				if (operStack.size()==0) {
					operStack.push(list.get(i).charAt(0));
					continue;
				}
				if ((char)operStack.peek()=='/') {
					numStack.push(caculate());
				}
				operStack.push(list.get(i).charAt(0));
			}
			else if (list.get(i).charAt(0)=='/') {
				if (operStack.size()==0) {
					operStack.push(list.get(i).charAt(0));
					continue;
				}
				operStack.push(list.get(i).charAt(0));
			}
			else {
				numStack.push(Float.parseFloat(list.get(i)));
			}
			
		}
		while (operStack.size()!=0) {
			
			numStack.push(caculate());
		}
		return (float) numStack.pop();
	}
	public float caculate(){
		char opf=(char) operStack.pop();
		float op2=(float) numStack.pop();
		float op1=(float) numStack.pop();
		float tmpResult = 0;
		switch(opf){  
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
