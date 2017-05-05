/**
 * 
 */
package com.coding.test;


import org.junit.Test;

import com.coding.datastructs.ArrayUtil;


public class ArrayUtilTest {
	    @Test
		public void reverseTest(){
		ArrayUtil array = new ArrayUtil();
		  int[] origin ={7,9,30,3,5,6,2,4,78,1,4,4567,3};
		array.reverseArray(origin);
		for(int i=0;i<origin.length;i++){
			System.out.print(origin[i]+",");
		}
	}
	    @Test
	public void removeZeroTest(){
		ArrayUtil array = new ArrayUtil();
		int[] originArray ={0,1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5,0};
		int[] newArray=array.removeZero(originArray);
		for(int i=0;i<newArray.length;i++){
			System.out.print(newArray[i]+",");
		}
	}
	    @Test
	public void mergeTest(){
		ArrayUtil array = new ArrayUtil();
		int[] a1 = {3, 5, 7,8}; 
		int[] a2 = {4, 5, 6,7};
		int[] a3 = array.merge(a1, a2);
		for(int i=0;i<a3.length;i++){
			System.out.print(a3[i]+",");
		}
		
	}
	    @Test
	public void growTest(){
		ArrayUtil array = new ArrayUtil();
		int[] oldArray = {2,3,6};
		oldArray = array.grow(oldArray, 3);
		for(int i=0;i<oldArray.length;i++){
			System.out.print(oldArray[i]+",");
		}
	}
	    @Test
	public void fibinaqiTest(){
		ArrayUtil array = new ArrayUtil();
		int[] a = array.fibonacci(56);
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+",");
		}
	}
	    @Test
	public void sushuTest(){
		ArrayUtil array = new ArrayUtil();
		int[] aa = array.getPrimes(100);
		for(int t=0;t<aa.length;t++){
			System.out.print(aa[t]+",");
		}
	}
	    @Test	
	public void perfectTest(){
		ArrayUtil array = new ArrayUtil();
		int[] aaa = array.getPerfectNumbers(1000);
		for(int t=0;t<aaa.length;t++){
			System.out.print(aaa[t]+",");
		}
	}
	    @Test	
	public void seperatorTest(){
		ArrayUtil array = new ArrayUtil();
		int[] arr = array.getPrimes(100);
		String str = array.join(arr, "-");
		System.out.println(str);
	}
		
		//
	

}
