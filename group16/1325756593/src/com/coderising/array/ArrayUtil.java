package com.coderising.array;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Arrays;

import com.dong.week1.ArrayList;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if(origin==null||origin.length==0){
			return;
		}
		int len = origin.length;
		for(int i=0;i<len/2;i++){
			int temp = origin[i];
			origin[i]=origin[len-i-1];
			origin[len-i-1]=temp;
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
		int newArrayLen=0;
		int len = oldArray.length;
		for(int i=0;i<len;i++){
			if(oldArray[i]!=0){
				newArrayLen++;
			}
		}
		int[] retArray = new int[newArrayLen];
		int retIndex=0;
		for(int i=0;i<len;i++){
			if(oldArray[i]!=0){
				retArray[retIndex++]=oldArray[i];
			}
		}
		return retArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int len1 = array1.length;
		int len2 = array2.length;	
		int index1=0,index2=0;
		ArrayList arrayList = new ArrayList();
		while(index1<len1&&index2<len2){
		
			if(array1[index1]>array2[index2]){
				arrayList.add(array2[index2]);
				index2++;
			}else if(array1[index1]==array2[index2]){
				arrayList.add(array2[index2]);
				index1++;
				index2++;
			}
			else{
				arrayList.add(array1[index1]);			
				index1++;
			}			
		}
		if(index1==len1){
			for(int i=index2;i<len2;i++){
				arrayList.add(array2[i]);
			}
			
		}else{
			for(int i=index1;i<len1;i++){
				arrayList.add(array1[i]);
			}
		}
		return ListToArray(arrayList);
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
		ArrayList arrayList = new ArrayList();
		int first=1;
		int second=1;
		int third=0;
		for(int i=1;i<max;i++){	
			if(i==1){
				arrayList.add(first);
				arrayList.add(second);
			}			
			third= first+second;
			if(third>=max){
				break;
			}
			arrayList.add(third);
			first = second;
			second=third;
			
			
		}
		return ListToArray(arrayList);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		ArrayList arrayList = new ArrayList();
		for(int i=2;i<max;i++){
			if(isPrime(i)){
				arrayList.add(i);
			}
		}
		return ListToArray(arrayList);
	}
	
	public int[] getAbsolutePrimes(int max){
		ArrayList arrayList = new ArrayList();
		for(int i=2;i<=max;i++){
			if(isPrime(i)){
				arrayList.add(i);
			}
		}		
		return ListToArray(arrayList);
	}
	
	public static int[] ListToArray(ArrayList arrayList){
		if(arrayList==null){
			return null;
		}
		int[] retArray = new int[arrayList.size()];
		for(int i=0;i<retArray.length;i++){
			retArray[i] =(int) arrayList.get(i);
		}
		return retArray;
	}
	
	
	
	private boolean isPrime(int input){
		
		if(input==2){
			return true;
		}
		for(int i=2;i<=Math.sqrt(input);i++){
			if(input%i==0){
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
		ArrayList arrayList = new ArrayList();
		if(max==1){
			arrayList.add(1);
		}
		for(int i=1;i<max;i++){
			if (new ArrayUtil().isPerfect(i)) {
				arrayList.add(i);
			}
		}
		
		 return ListToArray(arrayList);
	}
	
	
	public boolean isPerfect(int i){
		int itemp = i;
		if(i==1){
			return true;
		}
		int[] primes = this.getAbsolutePrimes(i);
		ArrayList arrayList = new ArrayList();
		arrayList.add(1);
		
		
		while(i!=1){
			for(int div:primes){
				if(i%div==0){
					arrayList.add(div);
					i=i/div;
				}
			}
		}
		int sum = getSum(arrayList);
		if(sum==itemp){
			return true;
		}
		return false;
	}

	private int getSum(ArrayList arrayList) {
		int sum = 0;
		for(int j=0;j<arrayList.size();j++){
			sum += (int)arrayList.get(j);
		}
		return sum;
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为""3-8-9
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator){
		StringBuffer sb = new StringBuffer();
		if(array==null){
			return null;
		}else{
			for(int i=0;i<array.length;i++){
				sb.append(array[i]);
				if(i!=array.length-1){
					sb.append(seperator);
				}
			}
		}
		return sb.toString();
	}
	

}
