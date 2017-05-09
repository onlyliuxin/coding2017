package com.coding.basic.stack.test;

import org.junit.Test;

import com.coding.basic.stack.TwoStackInOneArray;

import junit.framework.Assert;

public class TwoStackInOneArrayTest {
	
	@Test
	public void testTwoStack(){
		TwoStackInOneArray tso = new TwoStackInOneArray();
		tso.push1("aaa");
		tso.push1("bbb");
		tso.push1("ccc");
		tso.push1("ddd");
		tso.push2("zzz");
		tso.push2("yyy");
		Assert.assertEquals(6, tso.getSize());
		Assert.assertEquals("ddd", tso.pop1());
		Assert.assertEquals("ccc", tso.pop1());
		Assert.assertEquals("bbb", tso.pop1());
		Assert.assertEquals("aaa", tso.pop1());
		Assert.assertEquals("yyy", tso.pop2());
		Assert.assertEquals("zzz", tso.pop2());
		tso.push2("abcd");
		tso.push2("bcdf");
		Assert.assertEquals("bcdf", tso.pop2());
		Assert.assertEquals("abcd", tso.pop2());
		Assert.assertEquals(0, tso.getSize());
		
	}
	
}
