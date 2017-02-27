package array;

import static org.junit.Assert.*;
import static util.TestUtil.*;

import java.util.Arrays;

import static util.Print.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ArrayUtilTest {

	private int[] myArr;
	ArrayUtil au = new ArrayUtil();
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
		myArr = null;
	}

	
	@Test
	public void testReverseArray() {
		
		int size = getRandomNumber();
		int[] expected = getRandomIntArray(size);
		int[] myArr = Arrays.copyOf(expected, size);
		
		au.reverseArray(myArr);
		
		for (int i = 0; i < size; i++) {
			assertEquals(expected[i], myArr[size-1-i]);
		}
		
	}
		

		

	@Test
	public void testRemoveZero() {

	}

	@Test
	public void testMerge() {

	}

	@Test
	public void testGrow() {

	}

	@Test
	public void testFibonacci() {

	}

	@Test
	public void testGetPrimes() {

	}

	@Test
	public void testGetPerfectNumbers() {

	}

	@Test
	public void testJoin() {

	}

}
