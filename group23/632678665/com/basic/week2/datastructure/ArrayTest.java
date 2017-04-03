package com.basic.week2.datastructure;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ArrayTest {
	private ArrayUtil t=new ArrayUtil();
	@Test
	public void testReverseArray(){
		int [] data1={7, 9 , 30, 3};
		int [] data2={7, 9, 30, 3, 4};
		t.reverseArray(data2);
	}
	@Test
	public void testRemoveZero(){
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		System.out.println(Arrays.toString(t.removeZero(oldArr)));
	}
	@Test
	public void testMerge(){
		int [] a1 = {3, 5, 7,8};
		int [] a2 = {4, 5, 6,7};
		System.out.println(Arrays.toString(t.merge(a1, a2)));
	}
	@Test
	public void testGrow(){
		int [] oldArray = {2,3,6};
		System.out.println(Arrays.toString(t.grow(oldArray, 5)));
	}
	@Test
	public void testFibonacci(){
		System.out.println(Arrays.toString(t.fibonacci(1)));
	}
	@Test
	public void testGetPrimes(){
		System.out.println(Arrays.toString(t.getPrimes(23)));
	}
	@Test
	public void testGetPerfectNumbers(){
		System.out.println(Arrays.toString(t.getPerfectNumbers(7)));
	}
	@Test
	public void testJoin(){
		int [] array= {3,8,9};
		System.out.println(t.join(array, "-"));
	}
}
