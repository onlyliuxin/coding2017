
package week2.arrayutil;

import static org.junit.Assert.*;

import org.junit.Test;

import week2.arrayutil.ArrayUtil;

public class ArrayUtilTest {

	private ArrayUtil arrayUtil=new ArrayUtil();
	
	@Test
	public void testReverseArray(){
		
		int[] origin={1,2,3,4};
		arrayUtil.reverseArray(origin);
		assertArrayEquals(new int[]{4,3,2,1}, origin);
		
		int[] origin1={5,6,7};
		arrayUtil.reverseArray(origin1);
		assertArrayEquals(new int[]{7,6,5},origin1);		
	}
	
	@Test
	public void testRemoveArray(){
		
		int[] oldArray={0,0,1,2,0,0,3,4,0,0};
		int[] result=arrayUtil.removeZero(oldArray);
		assertArrayEquals(new int[]{1,2,3,4},result);
	}
	
	@Test
	public void testMerge(){
		int[] array1={3,5,7,8};
		int[] array2={4,5,6,7};
		int[] array=arrayUtil.merge(array1, array2);
		assertArrayEquals(new int[]{3,4,5,6,7,8}, array);
	}
	
	@Test
	public void testGrow(){
		int[] oldArray={2,3,6};
		int size=3;
		int[] newArray=new int[oldArray.length+size];
		newArray=arrayUtil.grow(oldArray, size);
		assertArrayEquals(new int[]{2,3,6,0,0,0},newArray);
	}
	
	@Test
	public void testFibonacci(){
		int max=1;
		int[] result=arrayUtil.fibonacci(max);
		assertArrayEquals(new int[0],result);
		
		max=2;
		int[] result1=arrayUtil.fibonacci(max);
		assertEquals(1,result1[0]);		
		
		max=15;
		int[] result2=arrayUtil.fibonacci(max);
		assertArrayEquals(new int[]{1,1,2,3,5,8,13},result2);		
	}
	
	@Test
	public void testGetPrime(){
		int max=-1;
		int[] result=arrayUtil.getPrimes(max);
		assertEquals(new int[0],result);
		
		max=23;
		result=arrayUtil.getPrimes(max);
		assertEquals(new int[]{2,3,5,7,11,13,17,19},result);	
		
	}
	
	@Test
	public void testGetPerfectNumbers(){
		int max=7;
		int[] result=arrayUtil.getPerfectNumbers(max);
		assertArrayEquals(new int[]{6},result);
		
		max=100;
		result=arrayUtil.getPerfectNumbers(max);
		assertArrayEquals(new int[]{6,28},result);
	}
	
	@Test
	public void testJoin(){
		String seperator="-";
		int[] array={3,5,8};
		String result=arrayUtil.join(array, seperator);
		assertEquals("3-5-8",result);
		
 	}
}
