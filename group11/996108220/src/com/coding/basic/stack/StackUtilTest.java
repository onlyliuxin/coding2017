package com.coding.basic.stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coding.basic.stack.expr.InfixExpr;

public class StackUtilTest {
	Stack s;
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testToString() {
		s=new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		s.push(6);
		
		Assert.assertEquals("[6,5,4,3,2,1]", s.toString());
	}

	@Test
	public void testReverse() {
		s=new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		s.push(6);
		StackUtil.reverse(s);
		//System.out.println(s.size());
		Assert.assertEquals("[1,2,3,4,5,6]", s.toString());
	}
	@Test
	public void testRemove() {
		s=new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		s.push(6);
		StackUtil.remove(s, 6);
		//System.out.println(s.toString());
		Assert.assertEquals("[5,4,3,2,1]", s.toString());
	}
	@Test
	public void testGetTop() {
		s=new Stack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		s.push(5);
		s.push(6);
		Object[] s1=StackUtil.getTop(s, s.size());
		for (int i = 0; i < s1.length; i++) {
			System.out.println(s1[i]);
		}
		//Assert.assertEquals("[5,6]", s1.toString());
	}
	@Test
	public void testIsValidPairs() {
		String s="([e{df])" ;
		Assert.assertEquals(false, StackUtil.isValidPairs(s));
	}
	//5-2+3 6/2*3 5-2+3*4 2+3-5
	@Test
	public void testevaluate() {
		InfixExpr expr = new InfixExpr("2+3*4+5");
		System.out.println(expr.evaluate());
		Assert.assertEquals(19, expr.evaluate(), 0.001f);
		InfixExpr expr1 = new InfixExpr("5-2+3");
		System.out.println(expr1.evaluate());
		Assert.assertEquals(6, expr1.evaluate(), 0.001f);
		InfixExpr expr2 = new InfixExpr("6/2*3");
		System.out.println(expr2.evaluate());
		Assert.assertEquals(9, expr2.evaluate(), 0.001f);
		InfixExpr expr3 = new InfixExpr("50-2+3*4");
		System.out.println(expr3.evaluate());
		Assert.assertEquals(60, expr3.evaluate(), 0.001f);
		InfixExpr expr4 = new InfixExpr("20+30-50");
		System.out.println(expr4.evaluate());
		Assert.assertEquals(0, expr4.evaluate(), 0.001f);
	}

}
