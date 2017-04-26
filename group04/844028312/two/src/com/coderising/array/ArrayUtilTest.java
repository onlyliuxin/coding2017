package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	private ArrayUtil arrayUtil;
	@Before
	public void setUp() throws Exception {
		arrayUtil=new ArrayUtil();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int []origin={1,2,3,4,5,6};
		arrayUtil.reverseArray(origin);
	}
	@Test
	public void testRemoveZero() {
		int []oleArray={0,0,1,2,3,0,0,0,4,5,6,0,0,0};
		int []newArray=arrayUtil.removeZero(oleArray);
		System.out.println(newArray);
	}
	@Test
	public void testMerge() {
		int []array1={2,4,5,6,7};
		int []array2={6,7,8,9,10};
		int []newArray=arrayUtil.merge(array1, array2);
		System.out.println(newArray);
	}
	@Test
	public void testGrow() {
		int []array1={2,4,2,5,9};
		int []newArray=arrayUtil.grow(array1, 3);
		System.out.println(newArray);
	}
	@Test
	public void testFibonacci() {
		int []newArray=arrayUtil.fibonacci(25);
		System.out.println(newArray);
	}
	@Test
	public void testGetPrimes() {
		int []newArray=arrayUtil.getPrimes(25);
		System.out.println(newArray);
	}
	@Test
	public void testGetPerfectNumbers() {
		int []newArray=arrayUtil.getPerfectNumbers(500);
		System.out.println(newArray);
	}
	@Test
	public void testJoin() {
		int []array1={2,4,2,5,9};
		String a=arrayUtil.join(array1, "-");
		System.out.println(a);
	}
	
}
