package com.coding.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.array.ArrayUtil;

public class ArrayUtilTest {
	
	private ArrayUtil arrayUtil;

	@Before
	public void setUp() throws Exception {
		this.arrayUtil = new ArrayUtil();
	}

	@After
	public void tearDown() throws Exception {
		this.arrayUtil = null;
	}

	@Test
	public void testReverseArray() {
		int[] origin1 = {7, 9 , 30, 3};
		int[] origin2 = {7, 9, 30, 3, 4};
		this.arrayUtil.reverseArray(origin1);
		assertEquals(3, origin1[0]);
		assertEquals(30, origin1[1]);
		assertEquals(9, origin1[2]);
		assertEquals(7, origin1[3]);
		this.arrayUtil.reverseArray(origin2);
		assertEquals(4, origin2[0]);
		assertEquals(3, origin2[1]);
		assertEquals(30, origin2[2]);
		assertEquals(9, origin2[3]);
		assertEquals(7, origin2[4]);
	}

	@Test
	public void testRemoveZero() {
		 int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		 int[] newArr = this.arrayUtil.removeZero(oldArr);
		 assertEquals(12, newArr.length);
		 assertEquals(6, newArr[4]);
		 int oldArr1[]={0,0,0,0};
		 int[] newArr1 = this.arrayUtil.removeZero(oldArr1);
		 assertEquals(0, newArr1.length);
	}

	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7,8};
		int[] a2 = {4, 5, 6,7};
		//[3,4,5,6,7,8]
		int[] a3 = this.arrayUtil.merge(a1, a2);
		assertEquals(6, a3.length);
		assertEquals(3, a3[0]);
		assertEquals(8, a3[5]);
		int[] arr1 = {3, 5, 7};
		int[] arr2 = {9,12,15,19};
		int[] arr3 = this.arrayUtil.merge(arr1, arr2);
		int[] arr4 = this.arrayUtil.merge(arr2, arr1);
		assertEquals(7, arr3.length);
		assertEquals(3, arr3[0]);
		assertEquals(19, arr3[6]);
		//assertEquals(arr3, arr4);
		assertEquals(7, arr4.length);
		assertEquals(3, arr4[0]);
		assertEquals(19, arr4[6]);
		int[] a = {};
		int[] arr =  this.arrayUtil.merge(a, a1);
		assertEquals(4, arr.length);
		assertEquals(3, arr[0]);
		assertEquals(8, arr[3]);
	}

	@Test
	public void testGrow() {
		int[] a1 = {2,3,6};
		int[] a2 = this.arrayUtil.grow(a1, 3);
		assertEquals(6, a2.length);
		assertEquals(3, a1[1]);
		assertEquals(0, a2[3]);
		int[] a3 = this.arrayUtil.grow(a1, 0);
		assertEquals(3, a3.length);
		int[] a4 = this.arrayUtil.grow(new int[0], 3);
		assertEquals(3, a4.length);
	}

	@Test
	public void testFibonacci() {
		int[] arr = this.arrayUtil.fibonacci(15);
		assertEquals(7, arr.length);
		assertEquals(13, arr[6]);
		int[] arr1 = this.arrayUtil.fibonacci(1);
		assertEquals(0, arr1.length);
	}

	@Test
	public void testGetPrimes() {
		int[] arr = this.arrayUtil.getPrimes(23);
		assertEquals(8, arr.length);
		assertEquals(true, arr[arr.length-1]<23);
		int[] arr1 = this.arrayUtil.getPrimes(2);
		assertEquals(0, arr1.length);
	}

	@Test
	public void testGetPerfectNumbers() {
		//6 28 496 8128 33550336
		int[] arr = this.arrayUtil.getPerfectNumbers(10000);
		assertEquals(4, arr.length);
		assertEquals(8128, arr[3]);
	}

	@Test
	public void testJoin() {
		int[] array= {3,8,9};
		String str = this.arrayUtil.join(array, "-");
		assertEquals("3-8-9", str);
		String str1 = this.arrayUtil.join(array, "9");
		assertEquals("39899", str1);
	}

}
