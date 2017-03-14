package com.basic.coding;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayUtil {
	/**

	 * 给定一个整形数组a , 对该数组的值进行置换

		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]

		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]

	 * @param origin

	 * @return

	 */

	public void reverseArray(int[] origin){
		/**
		 * 长度小于等于一的数组就用原来的长度
		 */
		if(origin.length>1){
			int Arrlength = (origin.length%2==0)?origin.length/2:(origin.length-1)/2;
			for(int i = 0;i<Arrlength;i++){
				int a = origin[i];
				int b = origin[origin.length-1-i];
				origin[i] = b;
				origin[origin.length-1-i] = a;
			}
		}
	}

	

	/**

	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   

	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   

	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
		134566547675
	 * @param oldArray

	 * @return

	 */

	

	public int[] removeZero(int[] oldArray){
		int len = 0;
		int[] temp = new int[oldArray.length];
		for(int x: oldArray){
			if(x!=0){
				temp[len++] = x;
			}
		}
		oldArray = new int[len];
		for(int i = 0;i < len;i ++){
			oldArray[i] = temp[i];
		}
		
		return oldArray;
	}

	

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	public int[] merge(int[] array1, int[] array2){
		/**
		 * 先合并两个数组
		 */
		int[] newArray = new int[array1.length+array2.length];
		System.arraycopy(array1,0,newArray,0,array1.length);
		System.arraycopy(array2,0,newArray,array1.length,array2.length);
		
		Set<Integer> set = new HashSet<Integer>();
		for(int x:newArray){
			set.add(x);
		}
		/**
		 * 此时set已经是一个不含重复数据的集合
		 */
		//int[] noRepeat =  (int[])set.toArray(new int[set.size()]);
		//Object[] array = set.toArray();
		Integer[] array = set.toArray(new Integer[set.size()]);
		Arrays.sort(array);
		int[] sortArray=new int[array.length];
		for(int i=0;i<array.length;i ++)
		{
			sortArray[i]=array[i].intValue();
		}
		return sortArray;
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
		int[] newArray = new int[oldArray.length+size];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		return newArray;
	}

	

	/**

	 * 斐波那契数列为：0,1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列

	 * 例如， max = 15 , 则返回的数组应该为 [0,1，1，2，3，5，8，13]

	 * max = 1, 则返回空数组 []

	 * @param max

	 * @return

	 */

	public int[] fibonacci(int max){
		int index = 0;
		if(max>1){
			for(int i = 1;i<max;i++){
				if(getFibo(i,0,1)>=max){
					index = i-1;
					break;
				}
			}
			int[] newArray = new int[index];
			for(int j = 0;j<index;j++){
				newArray[j] = getFibo(j+1,0,1);
			}
			return newArray;
			
		}
		return null;

	}
	public static int[] record = null;
	/**
	 * 
	 * @param i
	 * @param result1 默认为0
	 * @param result2 默认为1
	 * @return223 135   212 123
	 */
	private static int getFibo(int i,int result1,int result2) {
		if (i<=1)
			return result1;
		else{
			return getFibo(i-1,result2,result1+result2);
		}
    }

	

	/**

	 * 返回小于给定最大值max的所有素数数组

	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]

	 * @param max

	 * @return

	 */

	public int[] getPrimes(int max){
		int size = 0;
		boolean flag = false;
		int[] oldArray = new int[max];
		for(int i=2;i<max;i++){
			flag = true;
			for(int j =2;j<i;j++){
				if(i%j==0){
					flag = false;
					break;
				}
			}
			if(flag){
				oldArray[size++]=i;
			}
		}
		int[] newArray = new int[size];
		for(int k = 0;k<size;k++){
			newArray[k] = oldArray[k];
		}
		return newArray;

	}

	

	/**

	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3

	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数

	 * @param max

	 * @return

	 */

	public int[] getPerfectNumbers(int max){
		int size = 0;
		
		int[] oldArray = new int[max];
		for(int i = 1;i < max ;i++){
			int num = 0;
			for(int j = 1;j < i;j++){
				if(i%j==0){
					num+=j;
				}
			}
			if(num==i){
				oldArray[size++] = i;
			}
		}
		int[] newArray = new int[size];
		//System.out.println(size);
		for(int k=0;k<size;k++){
			newArray[k] = oldArray[k];
		}
		return newArray;
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
		if(array!=null){
			String str = "";
			for(int i = 0;i<array.length;i++){
				if(i!=array.length-1){
					str += (array[i]+seperator);
				}else{
					str += array[i];
				}
			}
			return str;
		}
		return null;
	}
}
