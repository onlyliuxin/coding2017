package com.coderising.array;

import com.coding.basic.impl.ArrayList;
import com.coding.basic.impl.LinkedList;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		for(int i=0; i < origin.length; i++){
			if(i >= origin.length-1-i){
				break;
			}
			int tmp = origin[i];
			origin[i] = origin[origin.length-i-1];
			origin[origin.length-i-1] = tmp;
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
		int size = 0,	// 记录非0数量
			tmp = 0;	// 需要比较的下一个位置
		for(int i=0; i < oldArray.length;){
			if(oldArray[i] == 0){
				int j=tmp==0?(i+1):tmp;
				for(;j < oldArray.length; j++){
					if(oldArray[j] == 0){
						continue;
					}else{
						oldArray[i] = oldArray[j];
						oldArray[j] = 0;
						size++;
						tmp = j+1;
						i=j;
						break;
					}
				}
			}else{
				i++;
				size++;
				continue;
			}
		}
		int a[] = new int[size];
		System.arraycopy(oldArray, 0, a, 0, size);
		return a;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		int[] a3 = new int[array1.length+array2.length];
		int i = array1.length-1,
			j = array2.length-1,
			k = a3.length-1;
		while(i >= 0 && j >= 0){
			if(array1[i] > array2[j]){
				a3[k--] = array1[i--]; 
			}else{
				a3[k--] = array2[j--];
			}
		}
		while(j >= 0){
			a3[k--] = array2[j--];
		}
		while(i >= 0){
			a3[k--] = array1[i--];
		}
			
		return  a3;
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
		int a[] = new int[oldArray.length+size];
		System.arraycopy(oldArray, 0, a, 0, oldArray.length);
		return a;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		if(max == 1){
			int[] a = {0};
			return a;
		}
		if(max == 2){
			int[] a = {0,1};
			return a;
		}
		if(max == 3){
			int[] a = {0,1,1,2};
            return a;
        }
        ArrayList list  = new ArrayList();
        list.add(0);
        list.add(1);
        list.add(1);
        list.add(2);
        int size = 4;
        for(int i = 3; i < max ; i++){
           if(i == ((int)list.get(size-1)+(int)list.get(size-2))){
        	   list.add(i);
        	   size++;
           }
        }
        int[] a = new int[size];
        for(int i = 0; i < size; i++){
        	a[i] = (int) list.get(i);
        }
		return a;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if(max < 2){
			int[] a ={};
			return a;
		}
		if(max == 2){
			int[] a ={2};
			return a;
		}
		LinkedList list = new LinkedList();
		list.add(2);
		for(int n = 3; n < max; n=n+2){
			int i=3;
			boolean flag = true;
			for(;i*i <= n;i=i+2){
				// 先排除偶数
				if(n%i == 0){
					flag = false;
					break;
				}
			}
			if(flag){
				list.add(n);
			}
		}
		int[] a = new int[list.size()];
		for(int i = 0; i < list.size(); i++){
			a[i] = (int) list.get(i);
		}
		return a;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if(max <= 6){
			int a[] = {};
			return a;
		}
		LinkedList list = new LinkedList();
		int p = 6,sum;
		for(; p < max; p++){
			sum = 0;
			for(int i=1; i < p % 2; i++){
				if(p % i == 0){
					sum += i;
				}
			}
			if(sum == p){
				list.add(p);
			}
		}
		int a[] = new int[list.size()];
		for(int i = 0; i < list.size(); i++){
			a[i] = (int) list.get(i);
		}
		return a;
		
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
		for(int i=0;i<array.length ; i++){
			seperator += array[i];
			if(i < array.length-1){
				seperator +="-";
			}
		}
		// StringBuider 实现
		/*StringBuilder s = new StringBuilder();
		for(int a: array){
			if(s.length() > 0){
				s.append("-");
			}
			s.append(a);
		}
		seperator = s.toString();*/
		return seperator;
	}
	
}
