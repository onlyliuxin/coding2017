package com.coding.basic.stack.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.stack.Stack;
import com.coding.basic.stack.StackUtil;


public class StackUtilTest {

	Stack s = null;
	
	@Before
	public void init(){
		s = new Stack();
		s.push("1");
		s.push("2");
		s.push("3");
		s.push("4");
		s.push("5");
	}
	@After
	public void destory(){
		s = null;
	}
	
	@Test
	public void testReverse(){
		StackUtil.reverse(s);
		String str = printInfo();
		Assert.assertEquals("1;2;3;4;5;",str);
	}
	
	@Test
	public void testRemove(){
		StackUtil.remove(s, "3");
		String str = printInfo();
		Assert.assertEquals("5;4;2;1;",str);
	}
	
	@Test
	public void testGetTop(){
		Object[] obj = StackUtil.getTop(s,3);
		Assert.assertEquals("3",obj[2]);
		String str = printInfo();
		Assert.assertEquals("5;4;3;2;1;",str);
	}
	
	@Test
	public void testisValidPairs(){
		boolean flag1 = StackUtil.isValidPairs("([b[[[((({x})))]]]y])");
		Assert.assertEquals(true,flag1);
		boolean flag2 = StackUtil.isValidPairs("([b{x]y})");
		Assert.assertEquals(false,flag2);
	}
	
	public String printInfo(){
		String str = "";
		while(s.size()>0){
			str+=s.pop()+";";
		}
		return str;
	}
	
}
