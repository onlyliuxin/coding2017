package com.coding.basic.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.Stack;

public class StackTest {

	@Test
	public void test() {
		Stack s = new Stack();
		s.push("gong");
		s.push("bo");
		s.push("jie");
		s.push("hao");
		s.push("ren");
		System.out.println(s.size());
		System.out.println(s.peek());
		s.pop();
		System.out.println(s.size());
		System.out.println(s.peek());
	}

}
