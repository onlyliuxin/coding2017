package com.coding.basic;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.coding.basic.array.ArrayUtil;

public class ArrayUtilTest {

	@Before
	public void setUp() throws Exception {
		
		
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReverseArray() {
		
		int[] origin = new int[]{1,2,3,4, 5};
		
		ArrayUtil.reverseArray(origin);
		
		System.out.println(Arrays.toString(origin));
		
	}
	
	@Test
	public void testFibonacciMax() {
		System.out.println(Arrays.toString(ArrayUtil.fibonacci(1)));
		System.out.println(Arrays.toString(ArrayUtil.fibonacci(2)));
		System.out.println(Arrays.toString(ArrayUtil.fibonacci(3)));
		System.out.println(Arrays.toString(ArrayUtil.fibonacci(10)));
		System.out.println(Arrays.toString(ArrayUtil.fibonacci(100)));
	}
	@Test
	public void testFibonacciMax2() {
		System.out.println(Arrays.toString(ArrayUtil.fibonacci2(1)));
		System.out.println(Arrays.toString(ArrayUtil.fibonacci2(2)));
		System.out.println(Arrays.toString(ArrayUtil.fibonacci2(3)));
		System.out.println(Arrays.toString(ArrayUtil.fibonacci2(10)));
		System.out.println(Arrays.toString(ArrayUtil.fibonacci2(100)));
	}
	

}
