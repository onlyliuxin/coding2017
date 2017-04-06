package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	private static ArrayUtil a=new ArrayUtil();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] array={3,5,7,9};
		a.reverseArray(array);
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]);
		}
	}

	@Test
	public void testRemoveZero() {
		int oldArr[]={1,0,6,0,5,7,0};
		int newArr[]=a.removeZero(oldArr);
		for(int i=0;i<newArr.length;i++){
			System.out.println(newArr[i]);
		}
	}

	@Test
	public void testMerge() {
		int[] a1 = {3, 5, 7,8};
		int[] a2 = {4, 8, 9,11,19,20};
		int[] a3=a.merge(a1, a2);
		for(int i=0;i<a3.length;i++){
			System.out.println(a3[i]);
		}
	}

	@Test
	public void testGrow() {
		int oldArr[]={1,0,6,0,5,7,0};
		int newArr[]=a.grow(oldArr, 5);
		System.out.println(newArr.length);
	}

	@Test
	public void testFibonacci() {
		int newArr[]=a.fibonacci(1);
		for(int i=0;i<newArr.length;i++){
			System.out.println(newArr[i]);
		}
	}

	@Test
	public void testGetPrimes() {
		int newArr[]=a.getPrimes(2);
		for(int i=0;i<newArr.length;i++){
			System.out.println(newArr[i]);
		}
	}

	@Test
	public void testGetPerfectNumbers() {
		int newArr[]=a.getPerfectNumbers(100);
		for(int i=0;i<newArr.length;i++){
			System.out.println(newArr[i]);
		}
	}

	@Test
	public void testJoin() {
		int oldArr[]={1,0,6,0,5,7,0};
		String result=a.join(oldArr, "-");
		System.out.println(result);


	}

}
