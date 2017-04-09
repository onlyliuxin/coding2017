package org.xukai.coderising.array;

import org.junit.Test;

public class ArrayUtilTest {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		for (int i = 0; i < origin.length; i++) {
			if (i == origin.length/2) {
				break;
			}
			swap(origin,i,origin.length - 1 -i);
		}
	}
	private void swap(int[] array,int a,int b){
		array[a] = array[a] + array[b];
		array[b] = array[a] - array[b];
		array[a] = array[a] - array[b];
	}

	@Test
	public void testReverseArray() {
		int[] test = new int[]{7, 9, 30,-11, 3, 4};
		reverseArray(test);
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}
	}
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int nullIndex = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				nullIndex ++;
			}
		}
		int[] newArray = new int[nullIndex];
		nullIndex = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				newArray[nullIndex] = oldArray[i];
				nullIndex++;
			}
		}
		return newArray;
	}
	@Test
	public void removeZero() {
		int[] test = new int[]{1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
		int[] newArray = removeZero(test);
		for (int i = 0; i < newArray.length; i++) {
			System.out.println(newArray[i]);
		}
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int index_a = 0;
		int index_b = 0;
		int[] array = new int[array1.length + array2.length];
		int i = 0 ;
		while(index_a < array1.length && index_b < array2.length){
			if (array1[index_a] < array2[index_b]) {
				array[i] = array1[index_a];
				index_a++;
				i++;
			} else if(array1[index_a] > array2[index_b]) {
				array[i] = array2[index_b];
				index_b++;
				i++;
			} else {
				array[i] = array1[index_a];
				i++;
				index_a++;
				index_b++;
			}
		}
		if (index_a == array1.length) {
			for (int j = index_b; j < array2.length; j++) {
				array[i] = array2[j];
				i++;
			}
		} else {
			for (int j = index_a; j < array1.length; j++) {
				array[i] = array1[j];
				i++;
			}
		}
		int[] result = new int[i];
		System.arraycopy(array,0,result,0,i);
		return  result;
	}

	@Test
	public void testMerge() {
		int[] test1 = new int[]{2,3, 5, 7,8};
		int[] test2 = new int[]{4, 5, 6,7,8,21,33};
		int[] newArray = merge(test1,test2);
		for (int i = 0; i < newArray.length; i++) {
			System.out.println(newArray[i]);
		}
	}
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int [] oldArray,  int size){
		int[] newArray = new int[oldArray.length + size];
		System.arraycopy(oldArray,0,newArray,0,oldArray.length);


		return newArray;
	}

	@Test
	public void testGrow() {
		int[] test1 = new int[]{2,3, 5, 7,8};
		int[] newArray = grow(test1, 5);
		for (int i = 0; i < newArray.length; i++) {
			System.out.println(newArray[i]);
		}
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		if (max <= 1) {
			return new int[0];
		}
		int index_a = 1;
		int index_b = 1;
		int temp = 0;
		int count = 2;
		while ((temp = index_a + index_b) < max) {
			index_a = index_b;
			index_b = temp;
			count++;
		}
		int[] newArray = new int[count];
		for (int i = count-1; i > -1 ; i--) {
			newArray[i] = index_b;
			temp = index_b - index_a;
			index_b = index_a;
			index_a = temp;
		}
		return newArray;
	}

	@Test
	public void testfibonacci() {
		int[] newArray = fibonacci(15);
		for (int i = 0; i < newArray.length; i++) {
			System.out.println(newArray[i]);
		}
	}
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] container = new int[5];
		int count = 0;
		for (int i = 3; i < max; i++) {
			if (isShusu(i)) {
				if (count == container.length) {
					container = grow(container,container.length << 1);
				}
				container[count] = i;
				count++;
			}

		}
		int[] array = new int[count];
		System.arraycopy(container,0,array,0,count);
		return array;
	}
	@Test
	public void testGetPrimes() {
		int[] newArray = getPrimes(4);
		for (int i = 0; i < newArray.length; i++) {
			System.out.println(newArray[i]);
		}
	}
	private boolean isShusu(int num){
		int sqrt = 1;
		while (sqrt * sqrt < num){
			sqrt++;
		}
		for (int i = 2; i < sqrt; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int[] container = new int[5];
		int count = 0;
		for (int i = 1; i < max; i++) {
			if (isWanshu(i)) {
				if (count == container.length) {
					container = grow(container,container.length << 1);
				}
				container[count] = i;
				count++;
			}

		}
		int[] array = new int[count];
		System.arraycopy(container,0,array,0,count);
		return array;
	}
	@Test
	public void testGetPerfectNumbers() {
		int[] newArray = getPerfectNumbers(29);
		for (int i = 0; i < newArray.length; i++) {
			System.out.println(newArray[i]);
		}
//		isWanshu(6);
	}
	private boolean isWanshu(int num){
		int sqrt = 1;
		while (sqrt * sqrt < num){
			sqrt++;
		}
		int sum = 1;
		for (int i = 2; i < sqrt; i++) {
			if (num % i == 0 ) {
				sum = sum + i + (num/i);
			}
		}
		if (sum == num) {
			return true;
		}
		return false;
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param
	 * @return
	 */
	public String join(int[] array, String seperator){
		String result = "";

		for (int i = 0; i < array.length; i++) {
			if (i != 0) {
				result = result + seperator;
			}
			result = result + array[i];
		}

		return result;
	}
	@Test
	public void testJoin() {
		int[] test = {1, 5, 8, 4};
		String seperator = "-";
		String join = join(test, seperator);
		System.out.println(join);
	}

}
