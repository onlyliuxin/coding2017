package com.coding.basic.array;


public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		
		int length = origin.length;
		int[] temp = new int[length];		
		for (int i = 0; i < length; i++) {
			temp[i] = origin[length-1-i];
		}
		System.arraycopy(temp, 0, origin, 0, length);		
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		
		int length = oldArray.length;
		int[] tempArr = new int[length];
		int j = 0;
		int zeroNum = 0;//储存0的个数
		for (int i = 0; i < length; i++) {
			if(oldArray[i]!=0){
				tempArr[j] = oldArray[i];
				j ++;
			}else{
				zeroNum ++;
			}
		}
		//删除数组尾端的0
		int[] newArray = new int[length-zeroNum];
		System.arraycopy(tempArr, 0, newArray, 0, length-zeroNum);
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
		
		int length1 = array1.length;
		int length2 = array2.length;
		int[] array3 = new int[length1 + length2];
		//将array1、array2的值加入array3中
		System.arraycopy(array1, 0, array3, 0, length1);	
		System.arraycopy(array2, 0, array3, length1, length2);
		int length = array3.length;
		int temp;
		//将array3冒泡排序
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length - i; j++) {
				if(array3[i]>array3[j+i]){
					temp = array3[i];
					array3[i] = array3[j+i];
					array3[j+i] = temp;
				}
			}
		}
		return  duplicate(array3);
	}
	
	/**
	 *去重
	 */
	private int[] duplicate(int[] array){
		
		for (int i = 1; i < array.length; i++) {
			if(array[i-1]==array[i]){
				array[i] = 0;
			}
		}
		return removeZero(array);
	}
	
	/**
	 * 位图法合并
	 * @param array1
	 * @param array2
	 * @return
	 */
	public int[] merge2(int[] array1, int[] array2){
		
		int bitSize = 0;
		int a = array1[array1.length-1] ;
		int b = array2[array2.length-1];
		bitSize =(a>b)?a:b;		
		boolean[] bitmap = new boolean[bitSize+1];
		for (int i = 0; i < array1.length; i++) {
			bitmap[array1[i]]=true;		
		}
		for (int i = 0; i < array2.length; i++) {
			bitmap[array2[i]]=true;		
		}

		ArrayList ls = new ArrayList();
		for (int i = 0; i < bitmap.length; i++) {
			if(bitmap[i]==true){
				ls.add(i);
			}
		}
		return objList2int(ls);
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
		System.arraycopy(oldArray, 0,newArray , 0, oldArray.length);
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
				
		int[] array = {};
		if(max <= 1)return array;	
		//生成 斐波那契数列的ArrayList集合
	    ArrayList ls = new ArrayList();
	    ls.add(1);ls.add(1);	    
		int next;int i = 1;				
		while(true){
			next = (int)ls.get(i) +(int)ls.get(i-1);
			if(next >= max){
				break;
			}
			ls.add(next);
			i ++;
		}
		return objList2int(ls);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){

		ArrayList primesList = new ArrayList();
		boolean flag;
		for (int i = 2; i < max; i++) {
			flag = false;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				if (i % j == 0) {
					flag =true;
					break;
				}
			}
			if(!flag){
				primesList.add(i);
			}			
		}
		return objList2int(primesList);
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		
		int temp;
		ArrayList perfectList = new ArrayList();
		for (int i = 6; i <= max; i++) {
			temp = 0;
			for (int j = 1; j <= (i/2); j++) {
				if(i%j == 0){
					temp += j;
				}
			}
			if(temp == i){
				perfectList.add(i);
			}
		}
		return objList2int(perfectList);
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
		
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			str.append(array[i]+seperator);
		}
		return str.substring(0, str.lastIndexOf(seperator));
	}
	
	/**
	 * 将存储int数据的ArrayList转换为int数组
	 * @param ls
	 * @return
	 */
	public int[] objList2int(ArrayList ls){
		
		Object[] objArr = ls.toArray();
		int[] array = new int[ls.size()];
		for (int i = 0; i < ls.size(); i++) {
			array[i] = (int) objArr[i];
		}
		return array;
	}
	
}
