package com.coderising.array;

import java.util.Arrays;

import my.collection.linear.MyArrayList;

public class ArrayUtil {
	
	/**
	 * 1给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int temp;
		int len = origin.length;
		for(int i=0; i<origin.length/2; i++){
			temp = origin[i];
			origin[i] = origin[--len];	//caution with --x and x--
			origin[len] = temp;
		}
		for(int m : origin){
			System.out.print(m + "\t");
		}
	}
	
	/**
	 * 2现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	public int[] removeZero(int[] oldArray){
		//calculate new array length
		int newLen = oldArray.length;
		for(int m : oldArray){
			if(m == 0){
				--newLen;
			}
		}
		//set new array
		int[] newArray = new int[newLen];
		int newPos = 0;
		for(int m : oldArray){
			if(m == 0){
				continue;
			}else{
				newArray[newPos++] = m;
			}
		}
		return newArray;
	}
	
	/**
	 * 3给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	public int[] merge(int[] array1, int[] array2){
		int[] retTmp = new int[array1.length + array2.length];
		int i=0,j=0,k=0,sameCount=0;
		//insert smaller array
		while(i<array1.length && j<array2.length){
			if(array1[i] < array2[j]){
				retTmp[k++] = array1[i++];
			}else if(array1[i] > array2[j]){
				retTmp[k++] = array2[j++];
			}else{
				j++;
				sameCount++;
			}
		}
		//insert remainder array
		while(i < array1.length){
			retTmp[k++] = array1[i++];
		}
		while(j < array2.length){
			retTmp[k++] = array2[j++];
		}
		int[] ret = new int[retTmp.length - sameCount];
		if(sameCount > 0){
			System.arraycopy(retTmp, 0, ret, 0, retTmp.length - sameCount);
		}
		return  ret;
	}
	
	/**
	 * 4把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持
	 * 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public int[] grow(int [] oldArray,  int size){
		int[] newArray = new int[oldArray.length + size]; 
		for(int i=0; i<oldArray.length; i++){	//there are default values
			newArray[i] = oldArray[i];	
		}
		return newArray;
	}
	
	/**
	 * 5斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		MyArrayList mlist = new MyArrayList(10);
		for(int i=1; i<max; i++){
			if(fi(i) < max){
				mlist.add(fi(i));
			}else{
				break;
			}
		}
		int[] ret = new int[mlist.size()];
		for(int i=0; i<mlist.size(); i++){
			ret[i] = Integer.parseInt(mlist.get(i).toString());
		}
		return ret;
	}
	//origin fibonacci function
	private int fi(int i){
		if(i <= 2){
			return 1;
		}else{
			return fi(i-2) + fi(i-1);
		}	
	}
	
	/**
	 * 6返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		String str = "";
		int cou = 0; 
		boolean isPrime;
		for(int i=2; i<max; i++){
			isPrime = true;
			for(int j=2; j<i; j++){
				if(i%j == 0){
					isPrime = false;
					break;
				}
			}
			if(isPrime){
				str += i + ",";
				cou++;
			}
		}
		//convert string to int array
		int[] ret = new int[cou];
		String[] strArray = str.substring(0, str.length()-1).split(",");
		for(int i=0; i<cou; i++){
			ret[i] = Integer.parseInt(strArray[i]);
		}
		return ret;
	}
	
	/**
	 * 7所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		MyArrayList perNumArrayList = new MyArrayList(10);
		
		for(int i=max; i>0; i--){
			//get divide numbers
			MyArrayList divideNumArrayList = new MyArrayList(10);
			for(int j=1; j<i; j++){
				if(i%j == 0){
					divideNumArrayList.add(j);
				}
			}
			//count divide number
			int tmpCount = 0;
			for(int j=0; j<divideNumArrayList.size(); j++){
				tmpCount += Integer.valueOf(divideNumArrayList.get(j).toString());
			}
			//judge if is a perfect number
			if(tmpCount == i){
				perNumArrayList.add(i);
			}
		}
		//set return perfect numbers array
		int[] perNumArray = new int[perNumArrayList.size()];	//声明位置导致perNumArrayList.size()并为获取到值
		for(int j=0; j<perNumArrayList.size(); j++){
			perNumArray[j] = Integer.valueOf(perNumArrayList.get(j).toString());
		}
		//reverse array 
		reverseArray(perNumArray);
		
		return perNumArray;
	}
	
	/**
	 * 8用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator){
		String joined = "";
		for(int x : array){
			joined += x + seperator;
		}
		joined = joined.substring(0, joined.length()-1);	//String index is a range, not a character. 
		return joined;
	}
}
