/**
 * 
 */
package com.coderising.array;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Administrator
 *
 */
public class ArrayUtilTest {
	private static ArrayUtil util;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		util = new ArrayUtil();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		util = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.coderising.array.ArrayUtil#reverseArray(int[])}.
	 */
	@Test
	public void testReverseArray() {
		int[] origin = {1,2,3,4,5,6,7};
		int[] expected = {7,6,5,4,3,2,1};
		util.reverseArray(origin);
		assertArrayEquals(expected,origin);
		int[] origin2 = {1,2};
		int[] expected2 = {2,1};
		util.reverseArray(origin2);
		assertArrayEquals(expected2,origin2);
	}

	/**
	 * Test method for {@link com.coderising.array.ArrayUtil#removeZero(int[])}.
	 */
	@Test
	public void testRemoveZero() {
		 int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};   
		 int expectedArr[]={1,3,4,5,6,6,5,4,7,6,7,5};
		 int newArr[]=util.removeZero(oldArr);
		 assertArrayEquals(expectedArr,newArr);
		 
		 int oldArr2[]={0,0,0,0,0,0};   
		 int expectedArr2[]={};
		 int newArr2[]=util.removeZero(oldArr2);
		 assertArrayEquals(expectedArr2,newArr2);
		 
		 int oldArr3[]={1,2,3,4,5};   
		 int expectedArr3[]={1,2,3,4,5};
		 int newArr3[]=util.removeZero(oldArr3);
		 assertArrayEquals(expectedArr3,newArr3);
	}

	/**
	 * Test method for {@link com.coderising.array.ArrayUtil#merge(int[], int[])}.
	 */
	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7,8};
		int[] a2 = {4, 5, 6,7};
		int[] a3 = {3,4,5,6,7,8};
		assertArrayEquals(a3,util.merge(a1, a2));
	}

	/**
	 * Test method for {@link com.coderising.array.ArrayUtil#grow(int[], int)}.
	 */
	@Test
	public void testGrow() {
		 int[] oldArr = {2,3,6};
		 int[] newArr ={2,3,6,0,0,0};
		 int size =3;
		 assertArrayEquals(newArr,util.grow(oldArr, size));
	}

	/**
	 * Test method for {@link com.coderising.array.ArrayUtil#fibonacci(int)}.
	 */
	@Test
	public void testFibonacci() {
		int[] expected = {1,1,2,3,5,8,13};
		assertArrayEquals(expected,util.fibonacci(15));
		int[] expected2 = {1,1};
		assertArrayEquals(expected2,util.fibonacci(2));
	}

	/**
	 * Test method for {@link com.coderising.array.ArrayUtil#getPrimes(int)}.
	 */
	@Test
	public void testGetPrimes() {
		int[] arr = {2,3,5,7,11,13,17,19};
		assertArrayEquals(arr,util.getPrimes(23));
	}

	/**
	 * Test method for {@link com.coderising.array.ArrayUtil#getPerfectNumbers(int)}.
	 */
	@Test
	public void testGetPerfectNumbers() {
		int arr[] = {6};
		assertArrayEquals(arr,util.getPerfectNumbers(7));
		int arr2[] = {6,28};
		assertArrayEquals(arr2,util.getPerfectNumbers(28));
	}

	/**
	 * Test method for {@link com.coderising.array.ArrayUtil#join(int[], java.lang.String)}.
	 */
	@Test
	public void testJoin() {
		int[] arr = {3,8,9};
		String seperator = "-";
		String expected = "3-8-9";
		assertTrue(expected.equals(util.join(arr, seperator)));
	}

}
