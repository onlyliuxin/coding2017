package com.coding.basic;

import static org.junit.Assert.*;

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
	

}
