package com.coderising.array;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayUtilTest {

	private ArrayUtil u;
	int[] array;

	@Before
	public void setUp() throws Exception {

		u = new ArrayUtil();

		array = new int[100];

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testReverseArray() {
		fail("Not yet implemented");
		for (int i = 0; i < 100; i++) {
			array[i] = i;

		}
		array = u.reverseArray(array);
		System.out.println("testReverseArray:" + Arrays.toString(array));

	}

	@Test
	public void testRemoveZero() {
		 fail("Not yet implemented");
		for (int i = 0; i < 100; i++) {
			if (i < 50) {
				array[i] = 0;
			} else {
				array[i] = i;
			}
		}
		array = u.removeZero(array);
		System.out.println("testRemoveZero:" + Arrays.toString(array));
	}

	@Test
	public void testMerge() {
		//fail("Not yet implemented");
		int[] intarray={0,1,2,3,4,5,6,7};
		int[] intarray0={10,11,12,13,14,15,16,17,0,3};
		int[] intarray1=u.merge(intarray, intarray0);
		System.out.println("testMerge:" + Arrays.toString(intarray1));
		
	}

	@Test
	public void testGrow() {
		//fail("Not yet implemented");
		int[] intarray={0,1,2,3,4,5,6,7};
		int[] intarray0={10,11,12,13,14,15,16,17,0,3};
		int[] intarray1=u.grow(intarray, 10);
		System.out.println("testGrow:" + Arrays.toString(intarray1));
		
	}

	@Test
	public void testFibonacci() {
		//fail("Not yet implemented");
		int[] intarray={0,1,2,3,4,5,6,7};
		int[] intarray0={10,11,12,13,14,15,16,17,0,3};
		int[] intarray1=u.fibonacci(intarray0, 17);
		System.out.println("testFibonacci:" + Arrays.toString(intarray1));
	}

	@Test
	public void testGetPrimes() {
		//fail("Not yet implemented");int[] intarray={0,1,2,3,4,5,6,7};
		int[] intarray0={10,11,12,13,14,15,16,17,0,3};
		int[] intarray1=u.getPrimes(10);
		System.out.println("testGetPrimes:" + Arrays.toString(intarray1));
		
	}

	@Test
	public void testGetPerfectNumbers() {
	//	fail("Not yet implemented");
		int[] intarray0={10,11,12,13,14,15,16,17,0,3};
		int[] intarray1=u.getPerfectNumbers(10);
		System.out.println("testGetPerfectNumbers:" + Arrays.toString(intarray1));
	}

	@Test
	public void testJoin() {
	//	fail("Not yet implemented");
		int[] intarray0={10,11,12,13,14,15,16,17,0,3};
		String str=u.join(intarray0, "++");
		System.out.println("testJoin:" + str);
	}

}
