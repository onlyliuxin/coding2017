package test.com.coding.basic.array;

import org.junit.Assert;
import org.junit.Test;

import com.coding.basic.array.ArrayList;
import com.coding.basic.array.ArrayUtil;

public class ArrayUtilTest {

	ArrayUtil au = new ArrayUtil();
	
	@Test
	public void testReverseArray() {
		
		int[] a = {7, 9, 30, 3, 4};
		int[] assertArray = {4,3,30,9,7};
		au.reverseArray(a);	
		assertResult(assertArray,a);
	}

	@Test
	public void testRemoveZero() {
		
		int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] assertArray = {1,3,4,5,6,6,5,4,7,6,7,5};
		int[] newArr = au.removeZero(oldArr);
		assertResult(assertArray,newArr);
		
	}

	@Test
	public void testMerge() {
	
		int[] a1 = {3, 5, 7,8};
		int[] a2 = {4, 5, 6,7};
		int[] assertArray = {3,4,5,6,7,8};		
		//int[] a3 = au.merge(a1, a2);
		int[] a3 = au.merge2(a1, a2);
		assertResult(assertArray,a3);
	}

	@Test
	public void testGrow() {
		
		int[] oldArray = {2,3,6};
		int size = 3;
		int[] assertArray = {2,3,6,0,0,0};
		int[] resultArr = au.grow(oldArray, size);
		assertResult(assertArray,resultArr);
	}

	@Test
	public void testFibonacci() {
		
		int[] assertArray = {1,1,2,3,5,8,13};
		int max = 15;
		int[] resultArr = au.fibonacci(max);
		assertResult(assertArray,resultArr);
		max = 0;
		int[] assertArray1 ={};
		int[] resultArr1 = au.fibonacci(max);
		assertResult(assertArray1,resultArr1);
	}

	@Test
	public void testGetPrimes() {
		
		int[] assertArray = {2,3,5,7,11,13,17,19};
		int max = 23;
		int[] resultArr = au.getPrimes(max);
		assertResult(assertArray,resultArr);
	}

	@Test
	public void testGetPerfectNumbers() {
		
		int[] assertArray = {6,28,496};
		int max = 496;
		int[] resultArr = au.getPerfectNumbers(max);
		assertResult(assertArray,resultArr);
	}

	@Test
	public void testJoin() {
		
		int[] array = {6,5,8,9};
		String seperator = "*";
		String resulStr = au.join(array, seperator);
		String assertStr = "6*5*8*9";
		Assert.assertEquals(assertStr, resulStr);
	}

	@Test
	public void testObjList2int() {
		
		ArrayList ls = new ArrayList();
		for (int i = 0; i < 10; i++) {
			ls.add(i);
		}
		int[] resulArr = au.objList2int(ls);
		Assert.assertEquals(ls.size(), resulArr.length);
		for (int i = 0; i < resulArr.length; i++) {
			Assert.assertEquals(i, resulArr[i]);
		}
	}

	/**
	 * 断言方法
	 * @param assertArr 断言集合
	 * @param resultArr 实际集合
	 */
	private void assertResult(int[] assertArr,int[] resultArr){
		
		Assert.assertEquals(assertArr.length,resultArr.length);
		for (int i = 0; i < resultArr.length; i++) {
			Assert.assertEquals(assertArr[i],resultArr[i]);
		}
	}
}
