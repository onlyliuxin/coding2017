package main.week02.practice;

import java.util.Arrays;
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
		int[][] array1 = {{0,1,2,5,6,9,11},
				{1,2,3},
				{}};
		int[][] array2 = {{0,3,8,15,16,20,50},{1},{}};
		for(int i=0;i<3;i++){
			System.out.println("前："+Arrays.toString(array1[i])+Arrays.toString(array2[i]));
			System.out.println("后："+Arrays.toString(util.merge(array1[i], array2[i])));			
		}
	}

	@Test
	public void testGrow() {
		int[][] origin = {{1,20,3,65,4,6,9,7},
				{},
				{1,0},
				{1,0,2,3},
				{23,0,0,32}};
		for(int[] a : origin){
			System.out.println("前："+Arrays.toString(a));
			System.out.println("后："+Arrays.toString(util.grow(a, 3)));			
		}
	}

	@Test
	public void testFibonacci() {
		int[] origin = {1,2,3,65,4,6,9,7};
		for(int a : origin){
			System.out.println(Arrays.toString(util.fibonacci(a)));			
		}
	}

	@Test
	public void testGetPrimes() {
		int[] origin = {1,2,3,65,4,6,9,7};
		for(int a : origin){
			System.out.println(Arrays.toString(util.getPrimes(a)));			
		}
	}

	@Test
	public void testGetPerfectNumbers() {
		int[] origin = {1,2,3,65,4,6,999,7};
		for(int a : origin){
			System.out.println(Arrays.toString(util.getPerfectNumbers(a)));			
		}
	}

	@Test
	public void testJoin() {
		int[][] origin = {{1,20,3,65,4,6,9,7},
				{},
				{1,0},
				{1,0,2,3},
				{23,0,0,32}};
		for(int[] a : origin){
			System.out.println(util.join(a , "="));
		}
	}

}
