package week02.array;

import org.junit.*;

/**
 * 
 * @author Hui Zhou
 * @version 1.0 2017-02-28
 *
 */

public class ArrayUtilTest {
	
	ArrayUtil array;

	@Before
	public void setUp() throws Exception {
		array = new ArrayUtil();
	}

	@Test
	public void testReverseArray() {
		int[] origin = {7,9,30,3};
		array.reverseArray(origin);
		Assert.assertArrayEquals(new int[]{3,30,9,7},origin);
		origin = new int[]{7,9,30,3,4};
		array.reverseArray(origin);
		Assert.assertArrayEquals(new int[]{4,3,30,9,7},origin);
	}

	@Test
	public void testRemoveZero() {
		 int[] oldArr={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		 Assert.assertArrayEquals(new int[]{1,3,4,5,6,6,5,4,7,6,7,5},array.removeZero(oldArr));
	}

	@Test
	public void testMerge() {
		int[] a1 = new int[]{3, 5, 7, 8};  int[] a2 = new int[]{4, 5, 6, 7};
		Assert.assertArrayEquals(new int[]{3,4,5,6,7,8}, array.merge(a1, a2));
	}

	@Test
	public void testGrow() {
		int[] oldArray = new int[]{2,3,6};
		Assert.assertArrayEquals(new int[]{2,3,6,0,0,0,},array.grow(oldArray, 3));
	}

	@Test
	public void testFibonacci() {
		Assert.assertArrayEquals(new int[]{1,1,2,3,5,8,13},array.fibonacci(15));
		Assert.assertArrayEquals(new int[]{},array.fibonacci(1));
	}

	@Test
	public void testGetPrimes() {
		Assert.assertArrayEquals(new int[]{2,3,5,7,11,13,17,19},array.getPrimes(23));
	}

	@Test
	public void testGetPerfectNumbers() {
		Assert.assertArrayEquals(new int[]{},array.getPerfectNumbers(6));
		Assert.assertArrayEquals(new int[]{6},array.getPerfectNumbers(10));
		Assert.assertArrayEquals(new int[]{6,28},array.getPerfectNumbers(30));
	}

	@Test
	public void testJoin() {
		int[] arr= new int[]{3,8,9};
		String seperator = "-";
		Assert.assertEquals("3-8-9", array.join(arr, seperator));
	}

}
