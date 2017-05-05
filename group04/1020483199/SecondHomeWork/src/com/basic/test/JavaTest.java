package com.basic.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.basic.coding.ArrayUtil;

public class JavaTest {

	@Test
	public void test() {
		ArrayUtil ary = new ArrayUtil();
		int[] origin = {1,2,3,4,5};
		ary.reverseArray(origin);
		for(int i=0;i<origin.length;i++){
			System.out.println(origin[i]);
		}
	}
	//{1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}

	@Test
	public void test1() {
		ArrayUtil ary = new ArrayUtil();
		int[] origin = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] removeZero = ary.removeZero(origin);
		for(int x:removeZero){
			System.out.print(x);
		}
	}
	
	@Test
	public void test2() {
		ArrayUtil ary = new ArrayUtil();
		int[] array1 = {3,5,7,8};
		int[] array2 = {4,5,6,7};
		int[] merge = ary.merge(array1, array2);
		for(int x:merge){
			System.out.print(x);
		}
	}
	
	@Test
	public void test3(){
		//[2,3,6]
		ArrayUtil ary = new ArrayUtil();
		int[] oldArray = {2,3,6};
		int[] grow = ary.grow(oldArray, 3);
		for(int x:grow){
			System.out.print(x);
		}
	}
	
	@Test
	public void test4(){
		ArrayUtil ary = new ArrayUtil();
		int[] fibonacci = ary.fibonacci(15);
		for(int x:fibonacci){
			System.out.println(x);
		}
	}
	
	/**
	 * jion方法测试
	 */
	@Test
	public void test5(){
		ArrayUtil ary = new ArrayUtil();
		int[] array = {3,8,9};
		String seperator = "-";
		String join = ary.join(array, seperator);
		System.out.println(join);
	}
	
	/**
	 * getPerfectNumbers测试方法
	 */
	@Test
	public void test6(){
		ArrayUtil ary = new ArrayUtil();
		int[] perfectNumbers = ary.getPerfectNumbers(7);
		for(int x:perfectNumbers){
			System.out.println(x);
		}
	}
	
	@Test
	public void test7(){
		ArrayUtil ary = new ArrayUtil();
		int[] primes = ary.getPrimes(23);
		for(int x : primes){
			System.out.println(x);
		}
	}
}
