package com.coding.test;

import com.coding.basic.stack.Stack;





public class Test {
	
	public static void main(String[] args){
		Stack s = new Stack();
		s.push('c');
		Object o = s.pop();
		System.out.println(o.equals('c'));
		
	}

}
