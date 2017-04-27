package com.coding.basic.stack.expr;

import java.util.List;
import java.util.Stack;

public class InfixToPostfix {

	public static List<Token> convert(String expr) {
		return null;
	}

	public static void main(String[] args) {
		Stack<Character> stack = new Stack<Character>();
		String expr = "1+((2+3)*4)-5";
		for(int i=0;i<expr.length(); i++){	
			char c = expr.charAt(i);
			if(c == '+'){
				stack.push(c);
			}
			else if(c == '*'){
				stack.push(c);
			}
			else if(c == ')'){
				System.out.print(stack.pop() + " ");
			}
			else if(c == '('){
				System.out.print(" ");
			} else{
				System.out.print(c +" ");
			}
		}
		System.out.println("");
	}

}
