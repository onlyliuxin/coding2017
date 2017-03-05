package com.coderising.array;

import org.junit.Test;

public class ArrayUtilTest {

	ArrayUtil al=new ArrayUtil();
	
	@Test
	public void testReverseArray(){
		int origin[]={1,2,3,4,5,6};
		int exchange[]=al.reverseArray(origin);
		for(int i=0;i<exchange.length;i++){
			System.out.println(exchange[i]);
		}
		
	}
	@Test
	public void testRemoveZero(){
		int origin[]={1,0,0,2,3,4,5,0,6,0};
		int exchange[]=al.removeZero(origin);
		for(int i=0;i<exchange.length;i++){
			System.out.println(exchange[i]);
		}
	}
	@Test
	public void testJoin(){
		int array[]= {3,8,9};
		String seperator = "-";
		String exchange=al.join(array, seperator);
		System.out.println(exchange);
	}
	@Test
	public void testGetPerfectNumbers(){
		int max=10000;
		int perfect[]=al.getPerfectNumbers(max);
		for(int i=0;i<perfect.length;i++){
			System.out.println(perfect[i]);
		}
	}
	@Test
	public void testGetPrimes(){
		int max=3;
		int perfect[]=al.getPrimes(max);
		for(int i=0;i<perfect.length;i++){
			System.out.println(perfect[i]);
		}
	}
	@Test
	public void testFibonacci(){
		int max=100000;
		int fibonacci[]=al.fibonacci(max);
		for(int i=0;i<fibonacci.length;i++){
			System.out.println(fibonacci[i]);
		}
	}
	@Test
	public void testGrow(){
		int [] oldArray={2,3,6};
		int size=3;
		int newArray[]=al.grow(oldArray,size);
		for(int i=0;i<newArray.length;i++){
			System.out.println(newArray[i]);
		}
	}
}
