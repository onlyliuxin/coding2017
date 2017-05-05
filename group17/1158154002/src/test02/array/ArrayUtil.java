package test02.array;

import java.util.ArrayList;
import java.util.Arrays;

import com.sun.javafx.image.impl.IntArgb;

public class ArrayUtil {
	
	public static void main(String[] args) {
//		int[] arr={7, 9, 30, 3, 4};
//		reverseArray(arr);
		
//		int[] oldArr={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
//		removeZero(oldArr);
		
//		int[] a1={3, 5, 7,8};
//		int[] a2={4, 5, 6,7};
//		merge(a1,a2);
		
//		int[] a1={3, 5, 7,8};
//		grow(a1, 3);
		
//		fibonacci(15);
		
//		getPrimes(23);
		
//		getPerfectNumbers(4000);
		
		int[] a={1,2,3,4};
		join(a, "-");
	}
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin){
		int[] newArr=new int[origin.length];
		int j=0;
		for (int i = origin.length-1; i >=0; i--) {
			newArr[j++]=origin[i];
		}
		System.out.println(Arrays.toString(newArr));
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray){
		ArrayList<Integer> list=new ArrayList<>();
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i]!=0) {
				list.add(oldArray[i]);
			}
		}
		
		int[] newArr=new int[list.size()];
		int j=0;
		for (int i : list) {
			newArr[j++]=i;
		}
		System.out.println(Arrays.toString(newArr));
		return newArr;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		ArrayList<Integer> list=new ArrayList<>();
		for (Integer arr1 : array1) {
			list.add(arr1);
		}
		
		for (Integer arr2 : array2) {
			if (!list.contains(arr2)) {
				list.add(arr2);
			}
		}
		
		int[] newArr=new int[list.size()];
		int i=0;
		for (int one : list) {
			newArr[i++]=one;
		}
		
		for (int j = 0; j < newArr.length-1; j++) {
			for (int k = 0; k < newArr.length-1-j; k++) {
				
				if (newArr[k]>newArr[k+1]) {
					int temp=newArr[k];
					newArr[k]=newArr[k+1];
					newArr[k+1]=temp;
				}
			}
		}
		System.out.println(Arrays.toString(newArr));
		return  newArr;
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
	public static int[] grow(int [] oldArray,  int size){
		int[] newArr=new int[oldArray.length+size];
		System.arraycopy(oldArray, 0, newArr, 0, oldArray.length);
		System.out.println(Arrays.toString(newArr));
		return newArr;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		if (max>1) {
			ArrayList<Integer> list=new ArrayList<>();
			list.add(1);
			list.add(1);
			while (list.get(list.size()-1)<max) {
				if ((list.get(list.size()-1)+list.get(list.size()-2))<max) {
					list.add(list.get(list.size()-1)+list.get(list.size()-2));
				}else {
					break;
				}
			}
			int[] newArr=new int[list.size()];
			int i=0;
			for (int item : list) {
				newArr[i++]=item;
			}
			System.out.println(Arrays.toString(newArr));
			return newArr;
		} else {
			return null;
		}
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max) {
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 2; i < max; i++) {
			int j = 2;
			while (j < i) {
//				System.out.println("i:"+i+",j:"+j+",i%j:"+(i%j));
				if (i%j==0) {
					break;
				}
				j++;
			}
			if (j==i) {
				list.add(i);
			}
		}

		int[] newArr = new int[list.size()];
		int i = 0;
		for (int item : list) {
			newArr[i++] = item;
		}
		System.out.println(Arrays.toString(newArr));

		return newArr;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		ArrayList<Integer> list = new ArrayList<>();	
		for (int i = 1; i < max; i++) {
			int j = 1;
			int sum=0;
			while (j < i) {
//				System.out.println("i:"+i+",j:"+j+",i%j:"+(i%j));
				if (i%j==0) {
					sum=sum+j;
				}
				j++;
			}
			
			if (sum==i) {
				list.add(i);
			}
		}			
		
		int[] newArr = new int[list.size()];
		int i = 0;
		for (int item : list) {
			newArr[i++] = item;
		}
		System.out.println(Arrays.toString(newArr));

		return newArr;
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator){
		String result=Arrays.toString(array).replace(" ", "").replace("[", "").replace("]", "").replace(",", seperator);
		System.out.println(result);
		return result;
	}
}
