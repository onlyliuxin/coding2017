package day20170226.homework.array;

import static org.junit.Assert.assertArrayEquals;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	
	private int[] origin;
	private int[] dst;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() {
		origin = new int[]{1, 99, 30, 55, 40, 13};
		dst = new int []{13, 40, 55, 30, 99, 1};
		ArrayUtil.reverseArray(origin);
		assertArrayEquals(dst, origin);
	}
	
	@Test
	public void testRemoveZero() {
		origin = new int[] {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		dst = new int[] {1,3,4,5,6,6,5,4,7,6,7,5};
		assertArrayEquals(dst, ArrayUtil.removeZero(origin));
	}
	
	@Test
	public void testMerge() {
		int[] a1 = new int[] {7, 3, 4, 8};  
		int[] a2 = new int[] {6, 4, 1, 5, 7};   
		int[] result = new int[] {1,3,4,5,6,7,8};
		
		assertArrayEquals(result, ArrayUtil.merge(a1, a2));
	}

	@Test
	public void testGrow() {
		int[] oldArray = new int[]{2,3,6};
		assertArrayEquals(new int[]{2,3,6,0,0,0}, ArrayUtil.grow(oldArray, 3));
	}
	
	@Test
	public void testFibonacci() {
		assertArrayEquals(new int[]{1,1,2,3,5,8,13}, ArrayUtil.fibonacci(15));
	}
	
	@Test
	public void testGetPrimes() {
		assertArrayEquals(new int[]{2,3,5,7,11,13,17,19}, ArrayUtil.getPrimes(23));
	}
	
	@Test
	public void testGetPerfectNumbers() {
		assertArrayEquals(new int[]{6,28,496}, ArrayUtil.getPerfectNumbers(497));
	}
	
	
	@Test
	public void testJoin() {
		Assert.assertEquals("3-8-9", ArrayUtil.join(new int[]{3, 8, 9}, "-"));
	}
	
	
}
