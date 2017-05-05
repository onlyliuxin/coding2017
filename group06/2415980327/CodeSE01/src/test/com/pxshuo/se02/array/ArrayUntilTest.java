package test.com.pxshuo.se02.array;

import org.junit.Assert;
import org.junit.Test;

import com.pxshuo.se02.array.*;

public class ArrayUntilTest {
	private ArrayUtil obj = new ArrayUtil();
	
	@Test
	public void reverseArrayTest() {
		int[] origin = {3, 30, 9,7}; 
		obj.reverseArray(origin);
		int[] expect = {7,9,30,3};
		Assert.assertArrayEquals(expect, origin);
	}
	
	@Test
	public void removeZeroTest() {
		int[] origin = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}; 
		int[] actual = obj.removeZero(origin);
		int[] expect = {1,3,4,5,6,6,5,4,7,6,7,5};
		Assert.assertArrayEquals(expect, actual);
	}
	
	@Test
	public void mergeTest() {
		int[] array1 = {3, 5, 7,8}; 
		int[] array2 = {4, 5, 6,7}; 
		int[] actual = obj.merge(array2,array1);
		int[] expect = {3,4,5,6,7,8};
		Assert.assertArrayEquals(expect, actual);
	}
	
	@Test
	public void growTest() {
		int[] origin = {2,3,6}; 
		int[] actual = obj.grow(origin,3);
		int[] expect = {2,3,6,0,0,0};
		Assert.assertArrayEquals(expect, actual);
	}
	
	@Test
	public void fibonacciTest() {
		int[] actual = obj.fibonacci(15);
		int[] expect = {1,1,2,3,5,8,13};
		Assert.assertArrayEquals(expect, actual);
	}
	
	@Test
	public void getPrimesTest() {
		int[] actual = obj.getPrimes(23);
		int[] expect = {2,3,5,7,11,13,17,19};
		Assert.assertArrayEquals(expect, actual);
	}
	
	@Test
	public void getPerfectNumbersTest() {
		int[] actual = obj.getPerfectNumbers(1000);
		int[] expect = {6,28,496};
		Assert.assertArrayEquals(expect, actual);
	}
	
	@Test
	public void joinTest() {
		int[] origin = {3,8,9}; 
		String actual = obj.join(origin, "-");
		String expect = "3-8-9";
		Assert.assertEquals(expect, actual);
	}
}
