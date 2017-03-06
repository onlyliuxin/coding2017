package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.MyStack;

public class MyStackTest {

	@Test
	public void test() {
		MyStack ms =new MyStack();
		ms.push("java001");
		ms.push("java002");
		ms.push("java003");
		ms.pop();
		ms.peek();
		System.out.println(ms.size());
		System.out.println(ms.isEmpty());
		System.out.println(ms.empty());
		
	}

}
