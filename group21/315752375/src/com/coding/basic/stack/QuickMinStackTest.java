package com.coding.basic.stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class QuickMinStackTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		QuickMinStack quickMinStack=new QuickMinStack();
		quickMinStack.push(1);
		quickMinStack.push(2);
		quickMinStack.pop();
		quickMinStack.push(3);
		quickMinStack.push(4);
		quickMinStack.push(5);
		System.out.println("pop():"+quickMinStack.pop());
		System.out.println("pop():"+quickMinStack.pop());
		System.out.println("pop():"+quickMinStack.pop());
		quickMinStack.push(6);
		quickMinStack.push(7);
		System.out.println("min:"+quickMinStack.findMin());
		while(!quickMinStack.isEmpty()){
			System.out.println(quickMinStack.pop());
		}
		
	}

}
