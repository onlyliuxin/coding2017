package com.vvv.array;

import org.junit.Test;

public class ArrayUtilTest {	
//    private int[] arr1 = {};
    private int[] arr2 = {0};
    private int[] arr3 = {1,5,6,7};
    private int[] arr4 = {3,4,5,6,7,8};
    private int[] arrf = {1,-1,0,-2,0,3,-5,8,13};
    
    @Test
	public void reverseArrayTest(){
		int[] arr = {1,2,1,1,3,4,5,5,4,7,6,7,0,5};
		printArray(arr);
		ArrayUtil.reverseArray(arr);
		printArray(arr);
	}
	
	@Test
	public void removeZeroTest(){
		int[] oldArr={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		oldArr = arrf;
		printArray(oldArr);
		int[] arr = ArrayUtil.removeZero(oldArr);
		printArray(arr);
	}
	
	@Test
	public void mergeTest(){
		printArray(arr3);
		printArray(arrf);
		int[] a = ArrayUtil.merge(arr3, arrf);
		printArray(a);
	}
	
	@Test
	public void growTest(){
		int len = 5;		
		int[] retArr = ArrayUtil.grow(arr2, len);
		printArray(arr2);
		printArray(retArr);
	}
	
	@Test
	public void fibonacciTest(){
		int max = 15;
		
		int[] retArr = ArrayUtil.fibonacci(max);
		printArray(retArr);
	}
	
	@Test
	public void getPrimesTest(){
		int max = 25;
		int[] arr = ArrayUtil.getPrimes(max);
		printArray(arr);
	}
	
	@Test
	public void getPerfectNumbersTest(){
		int max = 50000;
		int[] arr =ArrayUtil.getPerfectNumbers(max);
		printArray(arr);
	}

	@Test
	public void joinTest(){
		String separator = "@";
		System.out.println(ArrayUtil.join(arr4,separator));
	}
	
    private  void printArray(int[] arr){
    	if(arr==null) {
    		System.out.println("array null");
    		return;
    	}
    	System.out.print("{");
    	for(int i=0; i<arr.length; i++){
    		System.out.print(arr[i]+" ");    		
    	}
    	System.out.print("}"+"\n");
    }
    
}
