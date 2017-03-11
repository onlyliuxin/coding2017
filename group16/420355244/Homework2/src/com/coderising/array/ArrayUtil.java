package com.coderising.array;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin){
		for(int i = 0;i < origin.length/2; i++){
			int x = origin[i];
			origin[i] = origin[origin.length - i -1];
			origin[origin.length - i -1] = x;
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static  int[] removeZero(int[] oldArray){
		int zeroCount = 0;
		for(int i = 0;i < oldArray.length; i++){
			if(oldArray[i] == 0){
				zeroCount++;
			}
		}
		int[] newArr = new int[oldArray.length - zeroCount];
		int index = 0;
		for(int i = 0;i < oldArray.length; i++){
			if(oldArray[i] != 0){
				newArr[index] = oldArray[i];
				index++;
			}
		}
		return newArr;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static  int[] merge(int[] array1, int[] array2){
		//先对数组进行去重,记录重复的索引，后将两个数组合并，再进行排序
		int[] repeatedNum = new int[array1.length + array2.length];
		int repeatedCount = 0;
		for(int i = 0;i < array1.length; i++){
			for(int j = 0;j < array2.length; j++){
				if(array1[i] == array2[j]){
					repeatedNum[repeatedCount] = array1[i];
					repeatedCount++;
				}
			}
		}
		int [] combineArr = new int[array1.length + array2.length - repeatedCount];
		for(int i = 0;i < array1.length; i++){
			combineArr[i] = array1[i];
		}
		for(int i = 0;i < array2.length; i++){
			int index = array1.length -1;
			boolean same = false;
			for(int j = 0;j < repeatedNum.length; j++){
				if(array2[i] == repeatedNum[j]){
					same = true;
				}
			}
			if(!same){
				index += 1;
				combineArr[index] = array2[i];
			}
		}
		//冒泡排序
		for(int i = 0;i < combineArr.length;i++){
			for(int j = i + 1;j < combineArr.length;j++){
				if(combineArr[i] > combineArr[j]){
					int x = combineArr[i];
					combineArr[i] = combineArr[j];
					combineArr[j] = x;
				}
			}
		}
		return  combineArr;
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
	public static  int[] grow(int [] oldArray,  int size){
		int[] newArr = new int[oldArray.length + size];
		for(int i = 0;i < oldArray.length; i++){
			newArr[i] = oldArray[i];
		}
		return newArr;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static  int[] fibonacci(int max){
		if(max == 1){
			return null;
		}else{
			int length = 0;
			int dataBefore = 0;
			int dataAfter = 1;
			while(dataAfter < max){
				int date = dataAfter;
				dataAfter = dataAfter + dataBefore;
				dataBefore = date;
				length++;
			}
			int index = 0;
			int[] result = new int[length];
			dataBefore = 0;
			dataAfter = 1;
			while(dataAfter < max){
				result[index] = dataAfter;
				int date = dataAfter;
				dataAfter = dataAfter + dataBefore;
				dataBefore = date;
				index ++;
			}
			return result;
		}
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public  static int[] getPrimes(int max){
		int i = 1;
		int length = 0;
		while(i < max){
			i++;
			int search = 1;
		}
		return null;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static  int[] getPerfectNumbers(int max){
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
	public static  String join(int[] array, String seperator){
		StringBuilder sb = new StringBuilder();
		for(int i=0 ;i < array.length; i++){
			sb.append(String.valueOf(array[i]));
			if(i != array.length - 1){
				sb.append(seperator);
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		/*int[] a = {7, 9 , 30, 3};
		reverseArray(a);
		for (int i : a) {
			System.out.print(i+",");
		}*/
		/*int[] oldArr = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5} ;
		int[] newArr = removeZero(oldArr);
		for (int i : newArr) {
			System.out.print(i+",");
		}*/
		/*int[] a1 = {3, 5, 7,8};   
		int[] a2 = {4, 5, 6,7};
		int[] merge = merge(a1,a2);
		for (int i : merge) {
			System.out.print(i+",");
		}*/
		/*int[] oldArray = {2,3,6};
		int size = 3;
		int[] newArr = grow(oldArray, size);
		for (int i : newArr) {
			System.out.print(i+",");
		}*/
		/*int[] array= {3,8,9};
		String seperator = "-";
		String join = join(array, seperator);
		System.out.println(join);*/
		int[] fibonacci = fibonacci(15);
		for (int i : fibonacci) {
			System.out.print(i+",");
		}
	}
}
