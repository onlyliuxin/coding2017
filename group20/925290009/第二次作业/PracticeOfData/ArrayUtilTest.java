package org.Ralf.ArrayUtilTest;

import static org.junit.Assert.*;

import org.Ralf.ArrayUtil.ArrayUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ArrayUtilTest {

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void reverseArray() {
		int[] origin = {9,8,7,6,5,4,3,2,1};
		int[] originCopy = origin;
		int[] reverse = {1,2,3,4,5,6,7,8,9};
		ArrayUtil.reverseArray(origin);
		Assert.assertArrayEquals(origin, reverse);
		ArrayUtil.reverseArray(origin);
		Assert.assertArrayEquals(origin, originCopy);
	}
	
	@Test
	public void removeZero(){
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] newarr = ArrayUtil.removeZero(oldArr);
		int[] realArr = {1,3,4,5,6,6,5,4,7,6,7,5};
		Assert.assertArrayEquals(newarr, realArr);
	}
	
	@Test
	public void merge(){
		
		int[] a1 ={3, 5, 7,8};
		int[] a2 = {4, 5, 6,7};
		int[] newarr = ArrayUtil.merge(a1, a2);
		int[] realArr = {3,4,5,6,7,8};
		Assert.assertArrayEquals(newarr, realArr);
	}
	
	@Test
	public void grow(){
		int[] oldArray = {2,3,6};
		int[] realArr = {2,3,6,0,0,0};
		int[] newArray = ArrayUtil.grow(oldArray, 3);
		Assert.assertArrayEquals(newArray, realArr);
	}
	
	@Test
	public void fibonacci(){
		
		int[] realArr = {1,1,2,3,5,8,13};
		int[] newArray = ArrayUtil.fibonacci(15);
		Assert.assertArrayEquals(newArray, realArr);
	}
	@Test
	public void getPrimes(){
		int[] realArr = {2,3,5,7,11,13,17,19};
		int[] newArray = ArrayUtil.getPrimes(23);
		Assert.assertArrayEquals(newArray, realArr);
	}
	
	@Test
	public void getPerfectNumbers(){
		int[] realArr = {6, 28, 496, 8128};
		int[] newArray = ArrayUtil.getPerfectNumbers(10000);
		Assert.assertArrayEquals(newArray, realArr);
	}
	
	@Test
	public void join(){
		int[] realArr = {6, 28, 496, 8128};
		int[] newArray = ArrayUtil.getPerfectNumbers(10000);
		Assert.assertArrayEquals(newArray, realArr);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
