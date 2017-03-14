package com.zzk.coding2017.zuoye_2.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.zzk.coding2017.zuoye_2.array.ArrayUtil;

public class ArrayUtilTest {
	ArrayUtil au;
	/*@Before
	public void setUp() throws Exception {
		au = new ArrayUtil();
	}

	@Test
	public void testReverseArray() {
		
		int[] a = {1,2,3,4,5};
		int[] b = {1,2,3,4};
		int[] c = {};
		int[] d = {1};
		
		au.reverseArray(a);
		au.reverseArray(b);
		au.reverseArray(c);
		au.reverseArray(d);
		
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
		}
		System.out.println();
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i]);
		}
		System.out.println();
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i]);
		}
		System.out.println();
		for (int i = 0; i < d.length; i++) {
			System.out.print(d[i]);
		}
		fail("Not yet implemented");
	}

	@Before
	public void setUp2() throws Exception {
		au = new ArrayUtil();
		System.out.println("testRemoveZero ");
	}
	
	@Test
	public void testRemoveZero() {
		int[] a = {1,2,3,0,0,3};
		int[] b = {};
		int[] c = {0,0,0,0};
		int[] d = {1,2,3,3};
		a = au.removeZero(a);
		b = au.removeZero(b);
		c = au.removeZero(c);
		d = au.removeZero(d);
		System.out.println("a");
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i]);
		}
		
		System.out.println("c");
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i]);
		}
		System.out.println("d");
		for (int i = 0; i < d.length; i++) {
			System.out.print(d[i]);
		}
		System.out.println("b");
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i]);
		}
		fail("Not yet implemented");
	}*/
	

	@Before
	public void setUp3() throws Exception {
		au = new ArrayUtil();
		System.out.println("testMerge ");
	}
	@Test
	public void testMerge() {
		int[] a = {1,1,1,1,1,1,1,1,1,1};
		int[] b = {1,2,4,4,4,6,7,8,8,9,9,9,9,9,9};
		int[] c = au.merge(a, b);
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i]);
		}
		fail("Not yet implemented");
	}

	
	
	@Test
	public void testGrow() {
		fail("Not yet implemented");
	}

	@Before
	public void setUp5() throws Exception {
		au = new ArrayUtil();
		System.out.println("testFibonacci ");
	}
	@Test
	public void testFibonacci() {
		int[] result = au.fibonacci(10);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]+", ");
		}
		fail("Not yet implemented");
	}

	
	@Before
	public void setUp6() throws Exception {
		au = new ArrayUtil();
		System.out.println("testGetPrimes ");
	}
	@Test
	public void testGetPrimes() {
		int[] result = au.getPrimes(20);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]+", ");
		}
		fail("Not yet implemented");
	}

	@Test
	public void testGetPerfectNumbers() {
		fail("Not yet implemented");
	}

	
	
	@Before
	public void setUp7() throws Exception {
		au = new ArrayUtil();
		System.out.println("testJoin ");
	}
	@Test
	public void testJoin() {
		int[] test1 = {1};
		System.out.println(au.join(test1, "--"));
		int[] test2 = {1,2};
		System.out.println(au.join(test2, "--"));
		fail("Not yet implemented");
	}

}
