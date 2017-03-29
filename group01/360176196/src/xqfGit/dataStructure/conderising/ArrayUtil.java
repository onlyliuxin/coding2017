package xqfGit.dataStructure.conderising;

import java.util.Arrays;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int [] temp = new int[origin.length];
		for(int i =0;i<origin.length;i++){
			temp[origin.length-1-i]=origin[i];
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
		int num = 0;
		int index = 0;
		int [] temp = new int[oldArray.length];
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]!=0){
				temp[index] = oldArray[i];
				num =num +1;
				index++;
			}
		}
		return Arrays.copyOf(temp, num);
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int index = 0;
		int [] temp = new int[array1.length+array2.length];
		for(int j=0;j<array1.length;j++){
			for(int i =0;i<array2.length;i++){
				if(array1[j] > array2[i]){
					temp[index] = array2[i];
					index++;
				} if(array1[j] == array2[i]){
					temp[index] = array2[i];
					index++;
					continue;
				} if(array1[j] < array2[i]){
					temp[index] = array1[j];
					index++;
					continue;
				}
			}
		}
		return  temp;
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
		return Arrays.copyOf(oldArray, oldArray.length+size);
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		int n=0;
		int[] temp ={};
		if(max == 1){
			return temp;
		}else{
			for(int i =0;i<30;i++){
				if(fibo(i) > max){
					n = i;
					break;
				}
			}
			for(int i = 0;i < n;i++){
				temp = Arrays.copyOf(temp, i+1);
				temp[temp.length-1] = fibo(i);
			}
			return temp;
		}
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] temp ={};
		for(int i =2;i<max;i++){
			if(isPrimes(i)){
				temp = Arrays.copyOf(temp, i-1);
				temp[temp.length-1] = i;
			}
		}
		return removeZero(temp);
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
		StringBuffer temp = new StringBuffer();
		for(int i = 0;i<array.length;i++){
			if(i == array.length-1){
				temp.append(array[i]);
			}else{
				temp.append(array[i]+seperator);
			}
		}
		return temp.toString();
	}
	
	/*
	 * 菲波那切数列
	 */
	private int fibo(int n){
		if(n < 2){
			return 1;
		}
		return fibo(n-1)+fibo(n-2);
	}
	
	/*
	 * 判断一个数是否为素数(质数)
	 */
	private boolean isPrimes(int n){
		for(int i =2;i<=Math.sqrt(n);i++){
			if(n%i == 0)
		    return false;
		}
		return true;
	}
}
