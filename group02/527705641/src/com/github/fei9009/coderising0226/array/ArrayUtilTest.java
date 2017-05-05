package com.github.fei9009.coderising0226.array;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ArrayUtilTest
{
	private ArrayUtil myArray;
	
	@Before
	public void setUp() throws Exception
	{
		myArray = new ArrayUtil();
	}

	@Test
	public void testReverseArray()
	{
		int[] a = {1, 2, 1, 3, 5, 6};
		int[] b = {6, 5, 3, 1, 2, 1};
				
		myArray.reverseArray(a);
		assertArrayEquals(a, b);
		
		int[] c = new int[0];
		myArray.reverseArray(c);
		assertArrayEquals(c, new int[0]);

	}

	@Test
	public void testRemoveZero()
	{
		int[] oldArr= {1, 3, 4, 5, 0, 0, 6, 6, 0, 5, 4, 7, 6, 7, 1, 2, 0, 5};
		int b[] = {1, 3, 4, 5, 6, 6, 5, 4, 7, 6, 7, 1, 2, 5};  
		int[] c = myArray.removeZero(oldArr);
		assertArrayEquals(b, c);
		
		int[] d = null;
		int[] e = myArray.removeZero(d);
		assertNull(e);
		
	}

	@Test
	public void testMerge()
	{
		int a1[] = {1, 2, 3, 4, 5};
		int b1[] = {3, 4, 5, 6, 7, 8};
		int c1[] = {1, 2, 3, 4, 5, 6, 7, 8};
		int[] newArray1 = myArray.merge(a1, b1);
		assertArrayEquals(c1, newArray1);
		
		int a2[] = new int[0];
		int b2[] = {0, 2, 3, 6, 7, 8};
		int c2[] = {0, 2, 3, 6, 7, 8};
		int[] newArray2 = myArray.merge(a2, b2);
		assertArrayEquals(c2, newArray2);
		
		int a3[] = {0, 2, 3, 6, 7, 8};
		int b3[] = new int[0];
		int c3[] = {0, 2, 3, 6, 7, 8};
		int[] newArray3 = myArray.merge(a3, b3);
		assertArrayEquals(c3, newArray3);
		
		int[] a4 = null;
		int[] b4 = null;
		int[] newArray4 = myArray.merge(a4, b4);
		assertNull(newArray4);
	}

	@Rule  
	public ExpectedException expectedEx = ExpectedException.none(); 
	
	@Test
	public void testGrow()
	{
		int[] a = {3, 5, 7, 8, 9};
		int[] b = {3, 5, 7, 8, 9, 0, 0, 0};
		int[] newArray = myArray.grow(a, 3);
		assertArrayEquals(b, newArray);
		
		int[] c = null;
		int[] newArray1 = myArray.grow(c, 3);
		assertNull(newArray1);
		
		// size < 0 抛出异常
		expectedEx.expect(Exception.class);
		myArray.grow(a, -3);
	}
	
	@Test
	public void testFibonacci()
	{
		//max == 1时返回空数组
		int[] array1 =  myArray.fibonacci(1);
		int[] b = new int[0];
		assertArrayEquals(array1, b);
		
		
		int[] array2=  myArray.fibonacci(35);
		int[] c = {1, 1, 2, 3, 5, 8, 13, 21, 34 };
		assertArrayEquals(c, array2);
	}

	@Test
	public void testGetPrimes()
	{
		int[] a = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31};
		int[] array1 =  myArray.getPrimes(35);
		assertArrayEquals(a, array1);
		
		//max <= 2的时候没有素数，数组为空数组
		int[] array2 = myArray.getPrimes(1); 
		int[] b = new int[0];
		assertArrayEquals(array2, b);
	}

	@Test
	public void testGetPerfectNumbers()
	{
		int[] array =  myArray.getPerfectNumbers(10000);
		int[] a = {6, 28, 496, 8128 };
		assertArrayEquals(a, array);
	}

	@Test
	public void testJoin()
	{
		int[] Array0 = {3, 5, 7, 8, 9};
		String s0 = myArray.join(Array0, "-");
		String s1 = "3-5-7-8-9";
		assertEquals(s1, s0);
		
		int[] Array1 = {3};
		String s2 = myArray.join(Array1, "-");
		String s3 = "3";
		assertEquals(s2, s3);
		
		//传递空数组时，返回空字符串
		int[] Array2 = new int[0];
		String s4 = myArray.join(Array2, "-");
		String s5 = "";
		assertEquals(s4, s5);
	}

}


