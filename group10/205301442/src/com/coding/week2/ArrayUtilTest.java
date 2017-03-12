package com.coding.week2;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilTest {

	@Test
	public void testReverseArray() {
		int[] a = {1,2,3,4,5,6};
		int[] b=ArrayUtil.reverseArray(a);
		for(int i =0;i<b.length;i++){
	//		System.out.print(b[i]+"  ");		
	   }
	}
     
	@Test
	public void testRemoveZero() {
		int[] a = {0,1,2,3,0,0,4,0,5,6,0};
		int[] b=ArrayUtil.removeZero(a);
		printArray(b, "removeZero");
	}

	@Test
	public void testMerge() {
		int[] a = {2,3,6,8};
		int[] b = {1,3,5,7};
	  int[] newA= ArrayUtil.merge(a, b);
	  printArray(newA, "merge");
	}

	private void printArray(int[] newA,String name) {
		System.out.println("------------------"+name+"---------------------");
		  for(int i=0;i<newA.length;i++){
			  System.out.print(newA[i]+"\t");
			 
		  }
		  System.out.println();
	}

	@Test
	public void testGrow() {
		
	}

	@Test
	public void testFibonacci() {
		int[] arr = ArrayUtil.fibonacci(18);
		printArray(arr, "Fibonacci");
	}

	@Test
	public void testGetPrimes() {
		
		int[] arr = ArrayUtil.getPrimes(9);
		printArray(arr,"Primes");
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] arr = ArrayUtil.getPerfectNumbers(10);
		printArray(arr,"Perfect");
	}

	@Test
	public void testJoin() {
		int[] a = {1,2,3,4,5,6};
		String b=ArrayUtil.join(a, "-");
		//System.out.println(b);
	}

}
