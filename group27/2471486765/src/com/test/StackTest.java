package com.test;

import org.junit.Test;

import com.mycoding.Stack;

public class StackTest {
	
	@Test
	public void test1() {
		Stack stack = new Stack();
		
		for(int i=0;i<9;i++) {
			stack.push(i);
		}
		System.out.println(stack);
		System.out.println(stack.peek());
		System.out.println(stack.isEmpty());
	
		
		for(int i=0;i<9;i++) {
			stack.pop();
		}
		//程序有点问题，栈为空时输出了个“]”
		System.out.println(stack);
		
		System.out.println(stack.isEmpty());
	}
}
