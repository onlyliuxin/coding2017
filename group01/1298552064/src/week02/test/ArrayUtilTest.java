package week02.test;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import week02.array.ArrayUtil;

public class ArrayUtilTest {
	
	private ArrayUtil arrayUtil = null;
	@Before
	public void setUp() throws Exception {
		arrayUtil = new ArrayUtil();
	}

	@After
	public void tearDown() throws Exception {
		arrayUtil = null;
	}

	@Test
	public void testReverseArray() {
		int []a = new int[]{7, 9 , 30, 3};
		int []b = new int[]{7, 9, 30, 3, 4};
		
		arrayUtil.reverseArray(a);
		arrayUtil.reverseArray(b);
		
		Assert.assertArrayEquals(new int[]{3,30,9,7}, a);
		Assert.assertArrayEquals(new int[]{4,3,30,9,7}, b);
	}

	@Test
	public void testRemoveZero() {
		int []oldArr = new int[]{1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5} ;
		int []newArray = arrayUtil.removeZero(oldArr);
		Assert.assertArrayEquals(new int[]{1,3,4,5,6,6,5,4,7,6,7,5}, newArray);
	}

	@Test
	public void testMerge() {
		int []a1 = new int[]{3, 5, 7,8} ;
		int []a2 = new int[]{4, 5, 6, 6, 7, 7} ;
		int []a3 = arrayUtil.merge(a1, a2);
		Assert.assertArrayEquals(new int[]{3,4,5,6,7,8}, a3);
	}

	@Test
	public void testGrow() {
		int []oldArray = new int[]{2,3,6};
		int size = 3;
		int []newArray = arrayUtil.grow(oldArray, size);
		Assert.assertArrayEquals(new int[]{2,3,6,0,0,0}, newArray);
	}

	@Test
	public void testFibonacci() {
		int max = 15;
		int max2 = 1;
		int []newArray = arrayUtil.fibonacci(max);
		int []newArray2 = arrayUtil.fibonacci(max2);
		Assert.assertArrayEquals(new int[]{1,1,2,3,5,8,13}, newArray);
		Assert.assertArrayEquals(new int[]{}, newArray2);
	}

	@Test
	public void testGetPrimes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPerfectNumbers() {
		fail("Not yet implemented");
	}

	@Test
	public void testJoin() {
		int[] array = new int[] { 3, 8, 9 };
		String seperator = "-";
		String result = arrayUtil.join(array, seperator);
		Assert.assertEquals("3-8-9", result);
	}

}
