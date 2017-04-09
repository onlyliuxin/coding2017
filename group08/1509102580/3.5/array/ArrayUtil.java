package com.zzk.coding2017.zuoye_2.array;

import com.zzk.coding2017.zuoye_1.ArrayList;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int head = 0;int end = origin.length-1;//定义收尾两个指示量
		if(end<=head){
			return ;
		}else{
			int temp;
			while(end>head){
				temp = origin[head];
				origin[head] =origin[end];
				origin[end] = temp;
				head++;
				end--;
			}
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
		int length = oldArray.length;
		if(length<=0){
			return null;
		}else{
			ArrayList al = new ArrayList();
			for (int i = 0; i < oldArray.length; i++) {
				if(oldArray[i]!=0){
					al.add((Integer)oldArray[i]);
				}else{
					continue;
				}
			}
			int[] result = new int[al.size()];
			for (int i = 0; i < result.length; i++) {
				result[i]=(int)al.get(i);
			}
			return result;
		}
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int i=0,j=0;//i,j分别用于记录array1和array2的比较过程的指示
		ArrayList al = new ArrayList();
		while(i<array1.length&&j<array2.length){
			while(i<array1.length-1&&array1[i]==array1[i+1]) i++;//跳过array1中相等的值
			while(j<array2.length-1&&array2[j]==array2[j+1]) j++;//跳过array2中相等的值
			if(array1[i]<array2[j]){
				al.add((Integer)array1[i]);
				i++;
			}else if(array1[i]==array2[j]){//两个数组的值相等时，取array1,并跳过array2
				al.add((Integer)array2[j]);
				i++;
				j++;
			}else{
				al.add((Integer)array2[j]);
				j++;
			}
		}
		//将剩余的值加入
		if(i==array1.length){
			while(j<array2.length){
				while(j<array2.length-1&&array2[j]==array2[j+1]) j++;
				al.add((Integer)array2[j]);
				j++;
			}
		}else{
			while(i<array1.length){
				while(i<array1.length-1&&array1[i]==array1[i+1]) i++;
				al.add((Integer)array1[i]);
				i++;
			}
		}
		int[] result = new int[al.size()];
		for (int k = 0; k < result.length; k++) {
			result[k]=(int)al.get(k);
		}
		return result;
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
		int[] result = new int[oldArray.length + size];
		System.arraycopy(oldArray, 0, result, 0, oldArray.length-1);
		return result;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		if(max == 0) return null;
		if(max == 1) return new int[]{1};
		if(max == 2) return new int[]{1,1};
		int[] result = new int[max];
		result[0] = 1;
		result[1] = 1;
		for (int i = 2; i < max; i++) {
			result[i] = result[i-1] + result[i-2];
		}
		return result;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if(max<=1) return null;
		int[] tmp = new int[max+1];//记录1到max所有的数,1表示是素数，0表示不是素数
		for (int i = 1; i < tmp.length; i++) {//初始化,tmp[0]的位置可以存储当前确定的最大的素数
			tmp[i] = 1;
		}
		tmp[0] = 2;//第一个素数是2
		while(tmp[0] < max/2){//结束标志，当除数大于max的一半是，可以确定所有小于max的数是不是素数
			for (int i = tmp[0]+1; i < tmp.length; i++) {//去除一部分合数
				if(i%tmp[0]==0){//说明i一定不是素数
					tmp[i]=0;
				}
			}
			//从tmp[0]下一个不能被tmp[0]以下的素数整除的数开始，寻找下一个确定的素数。
			int k=tmp[0]+1;
			while(tmp[k]==0){
				k++;
			}
			while(k%tmp[0]==0) {
				tmp[k]=0;
				k++;
			}
			tmp[0]=k;
		}
		ArrayList al = new ArrayList();
		for (int i = 2; i < tmp.length; i++) {
			if(tmp[i]==1) al.add(i);
		}
		int[] result = new int[al.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = (int)al.get(i);
		}
		return result;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int[] pr = getPrimes(max);
		ArrayList al = new ArrayList();
		int tmp = 0;
		while(tmp < max){
			
		}
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
		if(array.length==0) return null;
		StringBuilder sb = new  StringBuilder();
		for (int i = 0; i < array.length-1; i++) {
			sb.append(array[i]).append(seperator);
		}
		sb.append(array[array.length-1]);
		return sb.toString();
	}
	

}
