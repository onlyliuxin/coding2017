package banshee.WorkTwo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {
	
	private ArrayUtil util;

	@Before
	public void setUp() throws Exception {
		util = new ArrayUtil();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverseArray() throws Exception {
		int[] origin1 = {7, 9 , 30, 3};
		int[] origin2 = {7, 9, 30, 3, 4};
		util.reverseArray(origin1);
		util.reverseArray(origin2);
		assertArrayEquals(new int[]{3, 30, 9,7}, origin1);
		assertArrayEquals(new int[]{4,3, 30 , 9,7}, origin2);
	}

	@Test
	public void testRemoveZero() throws Exception {
        int oldArr[] = {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 0, 5};
        assertArrayEquals(new int[]{1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 5}, util.removeZero(oldArr));
	}

	@Test
	public void testMerge() throws Exception{
        int[] arr1 = new int[]{3, 5, 7, 8};
        int[] arr2 = new int[]{4, 5, 6, 7};
        assertArrayEquals(new int[]{3, 4, 5, 6, 7, 8}, util.merge(arr1, arr2));
	}

	@Test
	public void testGrow() throws Exception{
        int[] old = new int[]{2, 3, 6};
        assertArrayEquals(util.grow(old, 3), new int[]{2, 3, 6, 0, 0, 0});
	}

	@Test
	public void testFibonacci() throws Exception {
		 assertArrayEquals(new int[]{1, 1, 2, 3, 5, 8, 13}, util.fibonacci(15));
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
	public void testJoin() throws Exception {
        int[] arr = new int[]{3, 8, 9};
        assertEquals("3-8-9", util.join(arr, "-"));
	}

}
