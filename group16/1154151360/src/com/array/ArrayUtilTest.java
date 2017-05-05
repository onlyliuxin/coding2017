package com.array;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
@SuppressWarnings("deprecation")
public class ArrayUtilTest {

	ArrayUtil util;
	
	@Before
	public void init(){
		util = new ArrayUtil();
	}
	
	@Test
	public void test_reverseArray() {
		int [] a = {7, 9, 30, 3, 4};
		a = util.reverseArray(a);
		Assert.assertEquals("[4,3,30,9,7]", toString(a));
	}
	
	@Test
	public void test_removeZero(){
		int [] oldArr={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		
		oldArr = util.removeZero(oldArr);
		
		Assert.assertEquals("[1,3,4,5,6,6,5,4,7,6,7,5]", toString(oldArr));
	}
	
	
	@Test
	public void test_merge(){
		int [] a1 = {3, 5, 7,8};
		int [] a2 = {4, 5, 6,7};
		int [] a3 = util.merge(a1, a2);
		
		Assert.assertEquals("[3,4,5,6,7,8]", toString(a3));

	}
	
	@Test
	public void test_grow(){
		int [] oldArray = {2,3,6};
		int size = 3;
		int [] newArray = util.grow(oldArray, size);
		
		Assert.assertEquals("[2,3,6,0,0,0]", toString(newArray));
	}
	
	
	@Test
	public void test_fibonacci(){
		
		int [] array = util.fibonacci(15);
		
		Assert.assertEquals("[1，1，2，3，5，8，13]", toString(array));

	}
	
	
	@Test
	public void test_getPrimes(){
		
		int [] array = util.getPrimes(23);
		Assert.assertEquals("[2,3,5,7,11,13,17,19]", toString(array));

	}
	
	@Test
	public void test_getPerfectNumbers(){
		int [] array = util.getPerfectNumbers(10);
		
		Assert.assertEquals("[6]", toString(array));

	}
	
	@Test
	public void test_join(){
		
		int [] array = {3,8,9};
		String result = util.join(array, "-");
		Assert.assertEquals("3-8-9", result);
	}
	
	
	public String toString(int [] array){
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for(int item: array){
			builder.append(item)
					.append(",");
		}
		builder.replace(builder.length() - 1, builder.length(), "");
		builder.append("]");
		return builder.toString();
		
	}
}
