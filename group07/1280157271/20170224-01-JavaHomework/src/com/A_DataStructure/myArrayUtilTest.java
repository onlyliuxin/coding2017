package com.A_DataStructure;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class myArrayUtilTest {
	private myArrayUtil test;

	@Before
	public void setUp() throws Exception {
		test = new myArrayUtil();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] a={7, 9, 30 , 3, 4};
		int[] b={4, 3, 30 , 9, 7};
		test.reverseArray(a);
		for(int i=0;i<5;i++){
			assertEquals(a[i],b[i]);
		}
	}

	@Test
	public void testRemoveZero() {
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] expectArr={1,3,4,5,6,6,5,4,7,6,7,5};
		int[] newArr;
		newArr=test.removeZero(oldArr);
		for(int i=0;i<expectArr.length;i++){
			assertEquals(newArr[i],expectArr[i]);
		}
	}

	@Test
	public void testMerge() {
		//3��ȥ�غϲ�//////////////
		int[] a1 = {3, 5, 7,8};
		int[] a2 = {4, 5, 6,7};
		int[] a3 = {3,4,5,6,7,8};
		int[] newArr3 = test.merge(a1, a2); 
		for(int  i=0;i<newArr3.length;i++){
			assertEquals(a3[i],newArr3[i]);
		}
		 
	}

	@Test
	public void testGrow() {
		int[]  oldArray4 = {2,3,6};
		int[] expectArr = {2,3,6,0,0,0};
		int[]  newArray4 = test.grow(oldArray4, 3); 
		for(int i=0;i<newArray4.length;i++){
			assertEquals(expectArr[i],newArray4[i]);
		}
	}

	@Test
	public void testFibonacci() {
		int[] expectArr =  {1,1,2,3,5,8,13};
		int[] newFibonacci = test .fibonacci( 14); 
		for(int i=0;i<newFibonacci.length;i++){
			assertEquals(expectArr[i],newFibonacci[i]);
		}
	}

	@Test
	public void testGetPrimes() {
		int[] expectArr =  {2,3,5,7,11,13,17,19};
		int[] newPrims = test .getPrimes(23); 
		for(int i=0;i<newPrims.length;i++){
			assertEquals(expectArr[i],newPrims[i]);
		} 
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] expectArr =  {6,28};
		int[] perfectNumber = test .getPerfectNumbers(29); 
		for(int i=0;i<perfectNumber.length;i++){
			assertEquals(expectArr[i],perfectNumber[i]);
		}
	}

	@Test
	public void testJoin() {
		 String expectStr = "3-8-9";
		 int[] array7= {3,8,9};
		 String seperator="-"; 
		 assertEquals(expectStr,test.join(array7, seperator));	  
	}

}
