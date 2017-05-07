package com.danny.hw2.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.danny.hw2.ArrayUtil;

public class ArrayUtilTest {

	@Test
	public void testReverseArray() {
//		 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
//				 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
		int[] testData = new int[]{7,9,30,3};
		int[] ans = new int[]{3,30,9,7};
		new ArrayUtil().reverseArray(testData);
		
		for (int i = 0; i < ans.length; i++) {
			assertEquals(ans[i], testData[i]);
		}
	}

	@Test
	public void testRemoveZero() {
		int[] testData = new int[]{1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] ans = new int[]{1,3,4,5,6,6,5,4,7,6,7,5};
	
		int[] test = new ArrayUtil().removeZero(testData);
		for (int i = 0; i < ans.length; i++) {
			assertEquals(ans[i], test[i]);
		}
	}

	@Test
	public void testMerge() {
//		 * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的 例如 a1 =
//				 * [3, 5, 7,8] a2 = [4, 5, 6,7] 则 a3 为[3,4,5,6,7,8] , 注意： 已经消除了重复
		int[] a1 = new int[]{3, 5, 7,8};
		int[] a2 = new int[]{4,5,6,7};
		
		int[] ans = new int[]{3,4,5,6,7,8};
		
		int[] test = new ArrayUtil().merge(a1, a2);
		
		for (int i = 0; i < test.length; i++) {
			assertEquals(ans[i], test[i]);
		}

	}

	@Test
	public void testGrow() {
//		把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
//		 * 注意，老数组的元素在新数组中需要保持 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
//		 * [2,3,6,0,0,0]
 
		int size = 3;
		int[] testData = new int[]{2,3,6};
		int[] test = new ArrayUtil().grow(testData, size);
		assertEquals(testData.length+size, test.length);
		
	}

	@Test
	public void testFibonacci() {
		int[] ans = new int[]{1,1,2,3,5,8,13};
		int[] array = new ArrayUtil().fibonacci(15);		
		for (int i = 0; i < ans.length; i++) {
			assertEquals(ans[i], array[i]);	
		}
		
	}

	@Test
	public void testGetPerfectNumbers() {
		
		int[] ans=new int[]{1,2,4,7,14};
		int[] array=new ArrayUtil().getPerfectNumbers(28);
		for (int i = 0; i < array.length; i++) {
			assertEquals(ans[i], array[i]);
		}
	}

	@Test
	public void testJoin() {
		//* 用seperator 把数组 array给连接起来 例如array= [3,8,9],
		//seperator = "-" 则返回值为"3-8-9"
		int[] test = new int[]{3,8,9};
		
		String test1 = new ArrayUtil().join(test, "-");
		assertEquals("3-8-9", test1);
	}

}
