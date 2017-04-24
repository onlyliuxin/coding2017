package com.github.ipk2015.coding2017.basic.array;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	ArrayUtil arrayUtil=new ArrayUtil();;
	@Before
	public void setUp() throws Exception {
	}
	private String toArrayString(int[] array){
		return Arrays.toString(array).replaceAll(" ", "");
	}
	@Test
	public void testReverseArray() {
		int[] array=new int[]{1,3,5,9,16};
		arrayUtil.reverseArray(array);
		assertEquals("[16,9,5,3,1]",toArrayString(array));
	}

	@Test
	public void testRemoveZero() {
		int[] array=new int[]{1,0,3,0,0,0,5,9,16,0,0};
		array=arrayUtil.removeZero(array);
		assertEquals("[1,3,5,9,16]",toArrayString(array));
	}

	@Test
	public void testMerge() {
		int[] array1=new int[]{1,3,5,9,16};
		int[] array2=new int[]{2,7,9,10,20};
		
		array1=arrayUtil.merge(array1, array2);
		System.out.println(toArrayString(array1));
		assertEquals("[1,2,3,5,7,9,10,16,20]",toArrayString(array1));
	}

	@Test
	public void testGrow() {
		int[] array=new int[]{1,3,5,9,16};
		array=arrayUtil.grow(array,5);
		assertEquals("[1,3,5,9,16,0,0,0,0,0]",toArrayString(array));
	}

	@Test
	public void testFibonacci() {
		int[] array=arrayUtil.fibonacci(20);
		assertEquals("[1,1,2,3,5,8,13]",toArrayString(array));
	}

	@Test
	public void testGetPrimes() {
		int[] array=arrayUtil.getPrimes(20);
		assertEquals("[2,3,5,7,11,13,17,19]",toArrayString(array));
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] array=arrayUtil.getPerfectNumbers(10000);
		assertEquals("[6,28,496,8128]",toArrayString(array));
	}

	@Test
	public void testJoin() {
		int[] array=new int[]{1,3,5,9,16};
		assertEquals("1-3-5-9-16",arrayUtil.join(array, "-"));
	}

}
