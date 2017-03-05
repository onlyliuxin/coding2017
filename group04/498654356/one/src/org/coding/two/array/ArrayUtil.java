package org.coding.two.array;

/**
 * 数组定长：考虑长度问题；排序之后的数组操作起来更方便；
 * @author Administrator
 *
 */
public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if(origin == null || origin.length < 2) {
			return;
		}
		int headIndex = 0;
		int lastIndex = origin.length - 1;
		while (headIndex < lastIndex) {
			int temp = origin[headIndex];
			origin[headIndex] = origin[lastIndex];
			origin[lastIndex] = temp;
			headIndex++;
			lastIndex--;
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
		if(oldArray == null || oldArray.length == 0) {
			return new int[]{};
		}
//		int[] newArray = removeZero1(oldArray);
		int[] newArray = removeZero2(oldArray);
		return newArray;
	}

	private int[] removeZero2(int[] oldArray) {
		int[] indexArray = new int [oldArray.length];
		int index = 0;
		for(int i = 0, size = oldArray.length; i < size ; i++) {
			if(oldArray[i] != 0) {
				indexArray[index] = oldArray[i];
				index++;
			}
		}
		if(index == 0) {
			return new int[]{};
		}
		int[] newArray = new int[index];
		System.arraycopy(indexArray, 0, newArray, 0, index);
		return newArray;
	}
	
	private int[] removeZero1(int[] oldArray) {
		int length = 0;
		for(int i = 0, size = oldArray.length; i < size ; i++) {
			if(oldArray[i] != 0) {
				length++;
			}
		}
		if(length == 0) {
			return new int[]{};
		}
		int[] newArray = new int[length];
		for(int i = 0, size = oldArray.length, index = 0; i < size ; i++) {
			if(oldArray[i] != 0) {
				newArray[index] = oldArray[i];
				index++;
			}
		}
		return newArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		boolean flag1 = (array1 == null || array1.length == 0);
		boolean flag2 = (array2 == null || array2.length == 0);
		if(flag1 && ! flag2) {	//array1 为空
			return array2;
		}
		if(flag2 && !flag1) {	//array2 为空
			return array1;
		}
		if(flag1 && flag2){		//array1 和 array2 为空
			return new int[0];
		}
		// array1 和 array2 都不为空
		int length1 = array1.length;
		int length2 = array2.length;
		int index1 = 0;
		int index2 = 0;
		int newLength = length1 + length2;
		int[] newArray = new int[newLength];
		int newIndex = 0;
		while (index1 < length1 && index2 < length2) {
			int val1 = array1[index1];
			int val2 = array2[index2];
			if(val1 < val2) {	//小于
				newArray[newIndex] = val1;
				newIndex++;
				index1++;
			} else if(val1 == val2) {	//等于
				newArray[newIndex] = val1;
				newIndex++;
				index1++;
				index2++;
			} else {	//大于
				newArray[newIndex] = val2;
				newIndex++;
				index2++;
			}
		}
		//剩余的
		while(index1 < length1) {
			newArray[newIndex] = array1[index1];
			newIndex++;
			index1++;
		}
		while(index2 < length2) {
			newArray[newIndex] = array2[index1];
			newIndex++;
			index2++;
		}
		//
		if(newIndex == newLength) {
			return newArray;
		}
		int[] resutlArray = new int[newIndex];
		System.arraycopy(newArray, 0, resutlArray, 0, newIndex);
		return  resutlArray;
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
		boolean flag = (oldArray == null || oldArray.length == 0);
		boolean flag2 = (size == 0);
		if(flag) {
			return new int[0];
		}
		if(flag2 && !flag) {
			return oldArray;
		}
		int length = oldArray.length;
		int[] newArray = new int[length + size];
		System.arraycopy(oldArray, 0, newArray, 0, length);
		return newArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		if(max < 2) {
			return new int[0];
		}
//		return fibonacci1(max);
		return fibonacci2(new int[]{1, 1}, max);
	}

	/**
	 * 递归
	 * @param array
	 * @param max
	 * @return
	 */
	private int[] fibonacci2(int[] array, int max) {
		int length = array.length;
		if((array[length - 1] + array[length -2]) >= max) {
			return array;
		}
		array = grow(array, 1);
		length = array.length;
		array[ length - 1] = array[length -2] + array[length -3];
		return fibonacci2(array, max);
	}
	
	/**
	 * 循环
	 * @param max
	 * @return
	 */
	private int[] fibonacci1(int max) {
		int[] array = new int[]{1, 1};
		int length = array.length;
		while((array[length - 1] + array[length -2]) < max) {
			array = grow(array, 1);
			length = array.length;
			array[ length - 1] = array[length -2] + array[length -3];
		}
		return array;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if(max < 3) {
			return new int[0];
		}
		int[] array = new int[]{2};
		int length = array.length;
		int val = array[length - 1] + 1;
		while(val < max) {
			if(isPrime(val)){
				array = grow(array, 1);
				length++;
				array[length - 1] = val;
			}
			val++;
		}
		return array;
	}
	
	private boolean isPrime(int val) {
		for(int i = 2; i < val; i++) {
			if(val % i == 0) {
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
		return null;
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator){
		if(array == null) {
			return null;
		}
		if(array.length == 0) {
			return "";
		}
		if(seperator == null) {
			seperator = "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i : array) {
			sb.append(i).append(seperator);
		}
		return sb.substring(0, sb.length() - seperator.length());
	}
	

}
