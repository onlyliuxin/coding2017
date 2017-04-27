package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ArrayUtilTest {

	ArrayUtil au = new ArrayUtil();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] a = {7, 9, 30, 3, 4};
		int[] ex= {4, 3, 30, 9, 7};
		int[] result = au.reverseArray(a);
		for(int i=0;i<a.length;i++){
			Assert.assertEquals(ex[i], result[i]);
		}
	}

	@Test
	public void testRemoveZero() {
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] result = au.removeZero(oldArr);
		int[] ex = {1, 3, 4, 5, 6, 6, 5, 4, 7, 6,7,5};
		for(int i=0;i<ex.length;i++){
			Assert.assertEquals(ex[i], result[i]);
		}
	}

	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7,8, 9, 15};
		int[] a2 = {4, 5, 6,7, 8, 12, 15};
		int[] ex = {3,4,5,6,7,8,9,12,15};
		
		int[] result = au.merge(a1, a2);
		for(int i=0;i<ex.length;i++){
			Assert.assertEquals(ex[i], result[i]);
		}
	}

	@Test
	public void testGrow() {
		int[] oldArray = {2,3,6};
		int size=6;
		int[] ex={2,3,6,0,0,0,0,0,0};
		
		int[] result=au.grow(oldArray, size);
		for(int i=0;i<ex.length;i++){
			Assert.assertEquals(ex[i], result[i]);
		}
	}

	@Test
	public void testFibonacci() {
		int[] ex = {1,1,2,3,5,8,13};
		int max=15;
		int[] result = au.fibonacci(max);
		
		for(int i=0;i<ex.length;i++){
			Assert.assertEquals(ex[i], result[i]);
		}
				
	}

	@Test
	public void testGetPrimes() {
		int[] ex = {2,3,5,7,11,13,17,19};
		int max = 23;
		int[] result = au.getPrimes(max);
		for(int i=0;i<ex.length;i++){
			Assert.assertEquals(ex[i], result[i]);
		}
	}

	@Test
	public void testGetPerfectNumbers() {
		int max = 10;
		int[] ex = {1, 6};
		int[] result = au.getPerfectNumbers(max);
		for(int i=0;i<ex.length;i++){
			Assert.assertEquals(ex[i], result[i]);
		}
	}

	@Test
	public void testJoin() {
		int[] array= {3,8,9};
		
		String sp = "__";
		String ex = "3__8__9";
		String result = au.join(array, sp);
		Assert.assertEquals(ex, result);
		
	}

}
