package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackTest {

	Stack stack = new Stack();
    @Before  
    public void before() throws Exception {  
		System.out.println("入栈");
    	int l = 3;
		System.out.println("长度："+l);
    	for(int i = 0; i < l; i++){
    		stack.push(i);
    		System.out.println(i);
    	}
    } 	

	@Test
	public void test() {

	}
	
    @After  
    public void after(){  
		System.out.println("出栈");
		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
		System.out.println("长度："+stack.size());
		System.out.println("**************");		
    }  
}
