package com.coding.basic.stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TwoStackInOneArrayTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		TwoStackInOneArray twoStackInOneArray=new TwoStackInOneArray();
		twoStackInOneArray.push1(1);
		twoStackInOneArray.push1(2);
		twoStackInOneArray.push1(3);
		twoStackInOneArray.push1(4);
		twoStackInOneArray.push1(5);
		twoStackInOneArray.push2(6);
		twoStackInOneArray.push2(7);
		twoStackInOneArray.push2(8);
		twoStackInOneArray.push2(9);
		twoStackInOneArray.push2(10);
		twoStackInOneArray.push2(11);
		System.out.println("pop:"+twoStackInOneArray.pop1());
		System.out.println("pop:"+twoStackInOneArray.pop1());
		System.out.println("pop:"+twoStackInOneArray.pop1());
		System.out.println("pop:"+twoStackInOneArray.pop1());
		System.out.println("pop:"+twoStackInOneArray.pop1());
		System.out.println("pop:"+twoStackInOneArray.pop2());
		System.out.println("pop:"+twoStackInOneArray.pop2());
		System.out.println("pop:"+twoStackInOneArray.pop2());
		System.out.println("pop:"+twoStackInOneArray.pop2());
		System.out.println("pop:"+twoStackInOneArray.pop2());
		System.out.println("pop:"+twoStackInOneArray.pop2());
	}

}
