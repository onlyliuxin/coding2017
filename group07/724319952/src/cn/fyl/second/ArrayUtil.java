package cn.fyl.second;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		//保存置换后的数组
		int[] temp = new int[origin.length];				
		
		for (int i = 0,j = origin.length - 1; i < origin.length; i++,j--) {
			//依次将origin的值赋给与temp前后对应位置上
			temp[i] = origin[j];					
			System.out.print(temp[i]+"  ");
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
		//保存oldArray数组去0后的容量
		int size = 0;
		
		for (int i = 0; i < oldArray.length; i++) {
			int temp = oldArray[i];
			if(temp != 0){
				size++;
			}
		}
		
		//初始化与oldArray数组去0后容量一样的新数组
		int[] newArray = new int[size];
		
		for (int i = 0,j = 0; i < oldArray.length; i++) {
			int temp = oldArray[i];
			//将不等于0的值赋给新数组
			if(temp != 0){
				newArray[j] = temp;
				j++;
			}
		}
		return newArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素，
	 *  并且仍然是有序的。
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		//保存新数组的容量
		int size = array1.length + array2.length;
		
		int[] newArray = new int[size];
		
		//先将array1数组存入temp数组
		for (int i = 0; i < array1.length; i++) {
			newArray[i] = array1[i];
		}			
		
		//增0，若相同的值是数组中倒数的二个的值，则需要一个零将最后一个覆盖掉
		array2 = grow(array2, 1);
		
		//将array1数组中每一个值依次与array2数组中每一个进行比较，若相同，则将相同的值覆盖掉
		for (int i = 0; i < array1.length; i++) {
			for (int j = 0; j < array2.length; j++) {
					if(array1[i] == array2[j]){
						for (int k = j; k < array2.length - 1; k++) {
							array2[k] = array2[k + 1];
						}
					}
				}
			}
		//去0
		array2 = removeZero(array2);
		
		//将array2的值插入newArray数组中
		for (int i = 0; i < array2.length; i++) {
			newArray[array1.length] = array2[i];
		}
		
		newArray = removeZero(newArray);
		
		//冒泡排序
		for (int i = 0; i < newArray.length - 1; i++) {
			for (int j = 0; j < newArray.length - 1; j++) {
				if(newArray[j] > newArray[j + 1]){
					int temp = newArray[j + 1];
					newArray[j+ 1] = newArray[j];
					newArray[j] = temp;
				}
			}
		}
		
		return  newArray;
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
		for (int i = 0; i < oldArray.length; i++) {
			newArray[i] = oldArray[i];
		}
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
		int[] array = {1,1,2,3,5,8,13,21};
		int[] newArray = null;
		for (int i = 0; i < array.length; i++) {
			if(array[i] > max){
				newArray = new int[i];
				System.arraycopy(array, 0, newArray, 0, i);
				break;
			}
		}
		return newArray;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] array = {0};
		if(max > 2){
			array[0] = 2;
			int k = 1;
		for (int n = 3; n < max; n++) {
			int i = 2;
			
			while(i < n){
				//若等于0，则不是素数，跳出循环
				if(n % i == 0)
					break;
				
					i++;
			}
			
			 //如果i==n则说明n不能被2~n-1整除，是素数
			 if (i == n) {    
				 //数组自增一个容量
				 	array =grow(array, 1);
				//将素数加入数组
					array[k] = n;
					k++;
	         }
			}
		return array;
		}
		else{
			return null;
		}
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		 
		int a =0;
		for(int i=2;i<max;i++){
			int sum=0;
			//查找因数
		for(int j=1;j<i;j++){
			if(i % j==0){
			sum += j;
		}
			if(sum==i)
				a = i;
			}
		}
		
		int[] array ={a};
		
		return array;
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
		String str  =null;
		int num = array.length;
		for (int i = 0; i < array.length; i++) {
			str += array[i] + seperator;
		}
		str = str.substring(4, str.length()-1);
		return str;
	}

}
