package test.week02.practice;

import static org.junit.Assert.*;

import java.util.Arrays;

import main.week02.practice.ArrayUtil;

import org.junit.Before;
import org.junit.Test;


public class ArrayUtilTest {

	ArrayUtil util;
	
	@Before
	public void setUp() throws Exception {
		util = new ArrayUtil();
	}
	
	@Test
	public void testReverseArray() {
		int[][] origin = {{1,20,5,3,65,4,6,9,7},
				{1},
				{1,2,3},
				{},
				{23,32}};
		for(int[] a : origin){
			System.out.println("前："+Arrays.toString(a));
			util.reverseArray(a);
			System.out.println("后："+Arrays.toString(a));			
		}
	}

	@Test
	public void testRemoveZero() {
		int[][] origin = {{1,20,0,0,5,3,65,4,0,6,9,7},
				{1,0},
				{1,0,2,3,0},
				{},
				{23,0,0,32}};
		for(int[] a : origin){
			System.out.println("前："+Arrays.toString(a));
			System.out.println("后："+Arrays.toString(util.removeZero(a)));			
		}
	}

	@Test
	public void testMerge() {
		fail("Not yet implemented");
	}

	@Test
	public void testGrow() {
		fail("Not yet implemented");
	}

	@Test
	public void testFibonacci() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

}
