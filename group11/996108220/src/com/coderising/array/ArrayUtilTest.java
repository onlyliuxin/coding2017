package com.coderising.array;
import org.junit.Test;



public class ArrayUtilTest {
	ArrayUtil arrayUtil=new ArrayUtil();

	@Test
	public void testReverseArray() {
		int[] array=new int[]{1,2,3,4,5};
		arrayUtil.reverseArray(array);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+" ");
		}
		//Assert.assertEquals(new int[]{5,4,3,2,1}, array);
	}
	@Test
	public void testRemoveZero(){
		int[] array=new int[]{1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}  ;
		int[] newarray=arrayUtil.removeZero(array);
		for (int i = 0; i < newarray.length; i++) {
			System.out.print(newarray[i]+" ");
		}	
	}
	@Test
	public void testMerge(){
		int[] array1=new int[]{3, 5, 7,8,9,10};
		int[] array2=new int[]{1,2,4};
		int[] newarray=arrayUtil.merge(array1, array2);
		for (int i = 0; i < newarray.length; i++) {
			System.out.print(newarray[i]+" ");
		}	
	}
	@Test
	public void testGrow(){
		int[] array=new int[]{1,3,4,5,0,0}  ;
		int[] newarray=arrayUtil.grow(array, 4);
		for (int i = 0; i < newarray.length; i++) {
			System.out.print(newarray[i]+" ");
		}	
	}
	@Test
	public void testFibonacci(){
		int[] newarray=arrayUtil.fibonacci(15);
		for (int i = 0; i < newarray.length; i++) {
			System.out.print(newarray[i]+" ");
		}	
	}
	@Test
	public void testGetPrimes(){
		int[] newarray=arrayUtil.getPrimes(15);
		for (int i = 0; i < newarray.length; i++) {
			System.out.print(newarray[i]+" ");
		}	
	}
	@Test
	public void testGetPerfectNumbers(){
		int[] newarray=arrayUtil.getPerfectNumbers(100);
		for (int i = 0; i < newarray.length; i++) {
			System.out.print(newarray[i]+" ");
		}	
	}
	@Test
	public void testJoin(){
		int[] array=new int[]{1,3,4,5,0,0} ;
		String s=arrayUtil.join(array,"&");
		System.out.print(s);		
	}

}
