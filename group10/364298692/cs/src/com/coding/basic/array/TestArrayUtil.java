package com.coding.basic.array;

import java.util.Date;

import org.junit.Test;

public class TestArrayUtil {
	
	@Test
	public void testArrayUtil(){
		int[] a = {7, 9 , 30, 3};
		for(int i : a){
			System.out.println(i);
		}
		System.out.println("reverse");
		ArrayUtil.reverseArray(a);
		for(int i : a){
			System.out.println(i);
		}
	}
	
	@Test
	public void testRemoveZero(){
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] newArr = ArrayUtil.removeZero(oldArr);
		for(int i : newArr){
			System.out.println(i);
		}
	}
	
	@Test
	public void testMerge(){
		int[] a1 = {3, 5, 7,8};
		int[] a2 = {4, 5, 6,7};
		int[] result = ArrayUtil.merge(a1, a2);
		for(int i : result){
			System.out.println(i);
		}
	}
	
	@Test
	public void testGrow(){
		int[] oldArray = {2,3,6};
		int[] newArr = ArrayUtil.grow(oldArray, 3);
		for(int i : newArr){
			System.out.println(i);
		}
	}
	
	@Test
	public void testFibonacci(){
		int[] fibonacci = ArrayUtil.fibonacci(20);
		for(int i : fibonacci){
			System.out.println(i);
		}
	}
	
	@Test
	public void testGetPrimes(){
		int[] primes = ArrayUtil.getPrimes(30);
		if(primes != null){
			for(int i : primes){
				System.out.println(i);
			}
		}
	}
	
	@Test
	public void testGetPerfectNumbers(){
		int[] perfectNumbers = ArrayUtil.getPerfectNumbers(200000);
		if(perfectNumbers != null){
			for(int i : perfectNumbers){
				System.out.println(i);
			}
		}
	}
	
	@Test
	public void testJoin(){
		int[] array = {3,8,9};
		String joinResult = ArrayUtil.join(array, "-");
				System.out.println(joinResult);
	}
	
}
