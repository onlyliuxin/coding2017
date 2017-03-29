package com.coderising.array;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReverseArray() {
		int[] a = {0,1,2,3,4,5,6,7,8,9};
		int[] rtnArray = ArrayUtil.reverseArray(a);
		for(int i=0;i<rtnArray.length;i++){
			System.out.print(rtnArray[i]);
			if(i<rtnArray.length-1){
				System.out.print(",");
			}
		}
		assertArrayEquals(rtnArray, new int[]{9,8,7,6,5,4,3,2,1,0});
	}

	@Test
	public void testRemoveZero() {
		int oldArray[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5} ;
		int[] newArray = ArrayUtil.removeZero(oldArray);
		for(int i=0;i<newArray.length;i++){
			System.out.print(newArray[i]);
			if(i<newArray.length-1){
				System.out.print(",");
			}
		}
		int[] eq = {1,3,4,5,6,6,5,4,7,6,7,5};
		assertArrayEquals(newArray,eq);
	}

	@Test
	public void testMerge() {
		int[] rtnArr = ArrayUtil.merge(new int[]{3,4,6,10}, new int[]{2,5,7,10});
		for(int i=0;i<rtnArr.length;i++){
			System.out.print(rtnArr[i]+",");
		}
	}

	@Test
	public void testGrow() {
		int oldArray[] ={1,2,3,4,5};
		int[] newArray = ArrayUtil.grow(oldArray, 5);
		for(int i=0;i<newArray.length;i++){
			System.out.print(newArray[i]);
			if(i<newArray.length-1){
				System.out.print(",");
			}
		}
		assertArrayEquals(newArray, new int[]{1,2,3,4,5,0,0,0,0,0});
	}

	@Test
	public void testFibonacci() {
		int[] rtnarr = ArrayUtil.fibonacci(15);
		for(int i=0;i<rtnarr.length;i++){
			System.out.print(rtnarr[i]+",");
		}
	}

	@Test
	public void testGetPrimes() {
		int[] arrs = ArrayUtil.getPrimes(23);
		for(int i=0;i<arrs.length;i++){
			System.out.print(arrs[i]+",");
		}
		assertArrayEquals(arrs, new int[]{2,3,5,7,11,13,17,19});
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] rtnArr = ArrayUtil.getPerfectNumbers(10000);
		for(int i=0;i<rtnArr.length;i++){
			System.out.println(rtnArr[i]);
		}
		assertArrayEquals(rtnArr, new int[]{6,28,496,8128});
	}

	@Test
	public void testJoin() {
		int[] array = new int[]{1,3,5,79,123};
		String seperator = "-";
		String arrStr = ArrayUtil.join(array, seperator);
		System.out.println(arrStr);
		assertEquals(arrStr, "1-3-5-79-123");
	}

}
