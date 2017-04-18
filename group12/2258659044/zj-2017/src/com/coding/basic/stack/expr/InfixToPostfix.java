package com.coding.basic.stack.expr;

import com.coding.basic.stack.Stack;
import com.coding.basic.stack.StackUtil;

public class InfixToPostfix {

    public static String convert(String expr) {
		
    	ExprIterator it = new ExprIterator(expr);
    	
		Stack<String> s1 = new Stack<>();

		Stack<String> s2 = new Stack<>();
		
		int preLevel,thisLevel;
		
		while(it.hasNext()){
			
			String element = it.next();
			if(Operator.contains(element)){//运算符
				while(true){
					if(s1.isEmpty()){
						s1.push(element);
						break;
					}else{									
						preLevel = Operator.getLevelByFlag(s1.peek());						
						thisLevel = Operator.getLevelByFlag(element);
						if(thisLevel>preLevel){
							s1.push(element);
							break;
						}else{						
							s2.push(s1.pop());
						}
					}
				}								
			}else{
				s2.push(element);
			}
		}
		
		while(!s1.isEmpty()){
			s2.push(s1.pop());
		}
		StackUtil.reverse(s2);
		return s2.toString();
	}
    
    public static void main(String[] args) {
		
    	String a = "9+(3-1)*3+10/2";
    	String post = InfixToPostfix.convert(a);
    	System.out.println(post);
    	
	}
}
