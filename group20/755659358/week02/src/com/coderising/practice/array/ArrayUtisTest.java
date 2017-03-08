package com.coderising.practice.array;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.coderising.practice.array.ArrayUtil;

public class ArrayUtisTest {
	
	private ArrayUtil util;
	
	@Before
	public void init(){
		util=new ArrayUtil();
	}
	

	@Test
	public void testReverse(){
		int [] origin={1,2,3,4,6};
		ArrayList<Integer> list=new ArrayList<>();
		list.add(1);
		list.add(2);
		util.reverseArray(origin);
		assertArrayEquals(new int[]{6,4,3,2,1}, origin);
	}
	
	@Test
	public void testRomoveZero(){
		int [] origin={1,2,3,0,4,0,6};
		assertArrayEquals(new int[]{1,2,3,4,6}, util.removeZero(origin));
	}
	
	@Test
	public void testMerge(){
		int [] a1={3,0,4,6};
		int [] a2={3,6,8,10};
		assertArrayEquals(new int[]{0,3,4,6,8,10}, util.merge(a1,a2));
	}
	
	@Test
	public void testGrow(){
		int [] a1={3,0,4,6};
		assertArrayEquals(new int[]{3,0,4,6,0,0}, util.grow(a1,2));
	}
	
	@Test
	public void testFibo(){
		assertArrayEquals(new int[]{1,1,2,3}, util.fibonacci(4));
	}
	
	@Test
	public void testGetPrime(){
		assertArrayEquals(new int[]{2,3,5,7,11}, util.getPrimes(13));
	}
	
	@Test
	public void testGetPerfectNum(){
		
		assertArrayEquals(new int[]{6,28,496}, util.getPerfectNumbers(1000));
	}
	
	@Test
	public void testJoin(){
		
		assertEquals("3-5-8", util.join(new int[]{3,5,8}, "-"));
	}

}
