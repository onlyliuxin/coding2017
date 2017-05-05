package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUntilTest {

	ArrayUntil arrayUntil=new ArrayUntil();
	
	@Test
	public void testReverseArray() {
		int[] arr1={7,9,30,3};
		int[] reArr1={3,30,9,7};
		int[] arr2={7,9,30,3,4};
		int[] reArr2={4,3,30,9,7};
		
		arrayUntil.reverseArray(arr1);
		arrayUntil.reverseArray(arr2);
		assertArrayEquals(reArr1,arr1);
		assertArrayEquals(reArr2,arr2);
	}

	@Test
	public void testRemoveZero() {
		int oldArr1[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int newArr1[]={1,3,4,5,6,6,5,4,7,6,7,5};
		int oldArr2[]={0,0,0,0,0,0};
		int newArr2[]={};
		
		assertArrayEquals(newArr1, arrayUntil.removeZero(oldArr1));
		assertArrayEquals(newArr2, arrayUntil.removeZero(oldArr2));
	}

	@Test
	public void testMerge() {
		int[] a1={3,5,7,8};
		int[] b1={4,5,6,7};
		int[] c1={3,4,5,6,7,8};

		int[] a2={};
		int[] b2={4,5,6,7};
		int[] c2={4,5,6,7};

		int[] a3={3,5,7,8};
		int[] b3={};
		int[] c3={3,5,7,8};
		
		int[] a4={};
		int[] b4={};
		int[] c4={};
		
		assertArrayEquals(c1, arrayUntil.merge(a1, b1));
		assertArrayEquals(c2, arrayUntil.merge(a2, b2));
		assertArrayEquals(c3, arrayUntil.merge(a3, b3));
		assertArrayEquals(c4, arrayUntil.merge(a4, b4));
	}

	@Test
	public void testGrow() {
		int[] oldArray={2,3,6};
		int[] newArray={2,3,6,0,0,0};
		int size=3;
		
		assertArrayEquals(newArray, arrayUntil.grow(oldArray, size));
	}

	@Test
	public void testFibonacci() {
		int max1=1;
		int max2=15;
		int max3=21;
		
		int[] fib1={};
		int[] fib2={1,1,2,3,5,8,13};
		int[] fib3={1,1,2,3,5,8,13};
		
		assertArrayEquals(fib1, arrayUntil.fibonacci(max1));
		assertArrayEquals(fib2, arrayUntil.fibonacci(max2));
		assertArrayEquals(fib3, arrayUntil.fibonacci(max3));
	}

	@Test
	public void testGetPrimes() {
		int max1=1;
		int max2=3;
		int max3=7;
		int max4=23;
		
		int[] primes1={};
		int[] primes2={2};
		int[] primes3={2,3,5};
		int[] primes4={2,3,5,7,11,13,17,19};
		
		assertArrayEquals(primes1, arrayUntil.getPrimes(max1));
		assertArrayEquals(primes2, arrayUntil.getPrimes(max2));
		assertArrayEquals(primes3, arrayUntil.getPrimes(max3));
		assertArrayEquals(primes4, arrayUntil.getPrimes(max4));
	}

	@Test
	public void testGetPerfectNumbers() {
		int max1=6;
		int max2=28;
		int max3=500;
		int[] perfectNumbers1={};
		int[] perfectNumbers2={6};
		int[] perfectNumbers3={6,28,496};
		
		assertArrayEquals(perfectNumbers1, arrayUntil.getPerfectNumbers(max1));
		assertArrayEquals(perfectNumbers2, arrayUntil.getPerfectNumbers(max2));
		assertArrayEquals(perfectNumbers3, arrayUntil.getPerfectNumbers(max3));
	}

	@Test
	public void testJoin() {
		String seperator="-";
		int[] arr={3,8,9};
		String string="3-8-9";
		
		assertEquals(string,arrayUntil.join(arr, seperator));
	}

}
