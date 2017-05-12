package data_Structure_Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		
		if(origin == null || origin.length == 0){
			return;
		}
		int len = origin.length;
		for(int i = 0; i < len/2; i++){
			int temp = origin[i];
			origin[i] = origin[len-1-i];
			origin[len-1-i] = temp;
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
		if(oldArray == null){
			return null;
		}
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		int len = oldArray.length;
		for(int i = 0; i < len; i++)
		{
			if(oldArray[i] != 0)
			{
				al.add(oldArray[i]);
			}
		}
		
		int arrLen = al.size();
		int[] arr = new int[arrLen];
		for(int i = 0; i < arrLen; i++)
		{
			arr[i] = al.get(i);
		}
		
		return arr;
		

	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		if(array1 == null){
			return array2;
		}
		if(array2 == null){
			return array1;
		}
		
		TreeSet<Integer> tr = new TreeSet<Integer>();
		for(int i = 0; i < array1.length; i++)
		{
			tr.add(array1[i]);
		}
		for(int j = 0; j < array2.length; j++)
		{
			tr.add(array2[j]);
		}
		
		int arrLen = tr.size();
		int[] arr = new int[arrLen];
		int index = 0;
		
		Iterator<Integer> it = tr.iterator();
		while(it.hasNext())
		{
			arr[index] = (Integer) it.next();
			index++;
		}
		
		return  arr;
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
		if(oldArray == null){
			return null;
		}
		if(size<0){
			throw new IndexOutOfBoundsException("size < 0");
		}
		int[] newArray = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
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
		if(max == 1){
			return new int[0];
		}
		if(max == 2){
			return new int[]{1, 1};
		}
		
		int[] arr = new int[max];//分配了多的空间，后面需要做数组拷贝
		arr[0] = 1;
		arr[1] = 2;
		int count = 2;//已经有两个元素了
		for(int i = 2; i < max; i++){
			arr[i] = arr[i-2] + arr[i-1];
			if(arr[i] >= max){
				break;
			}
			count++;
		}
		
		return Arrays.copyOf(arr, count);
		
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		
		if(max < 3){
			return new int[0];
		}
		
		int[] arr = new int[max];
		int count = 0;
		for(int n = 2; n < max; n++){
			if(isPrime(n)){
				arr[count++] = n;
			}
		}
		
		
		return Arrays.copyOf(arr, count);
		
	}
	
	// 判断某个数是否是素数
	private boolean isPrime(int n){
		int i = 2;
		while(i < n){
			if(n % i != 0){
				i++;
			}else{
				break;
			}
		}
		return i == n;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if (max <= 0) {
		    return null;
		}
		int[] array = new int[max];
		int count = 0;
		
		for(int n = 2; n < max; n++){
			int sum = 0;//记录所有真因子之和
			for(int i = 1; i < n; i++){
				if(n%i == 0){
					sum += i;
				}
			}
			if(sum == n){
				array[count++] = n;
			}
		}

		return Arrays.copyOf(array, count);
	}
	
//	public boolean isPerfectNumber(int number)
//	{
//		ArrayList<Integer> al = new ArrayList<Integer>();
//		
//		for(int i = 1; i < number; i++)
//		{
//			if(number % i == 0)
//				al.add(i);
//		}
//		
//		int value = 0;
//		for(int j = 0; j < al.size(); j++)
//		{
//			value = value + al.get(j);
//		}
//		
//		return value == number;
//	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator){
		
		if(array == null){
			return null;
		}
		if(array.length == 0){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < array.length; i++){
			sb.append(array[i]);
			if(i < array.length -1){
				sb.append(seperator);
			}
		}
		return sb.toString();
	}
	
	

}
