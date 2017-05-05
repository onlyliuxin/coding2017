package com.coderising.array;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] o){
		
		int length=o.length;
		int half=length/2;
		if(length==0||length==1){
			return;
		}
		for(int i=0;i<half;i++){
			int temp=o[i];
			o[i]=o[length-i-1];
			o[length-i-1]=temp;
		}
		for(int q=0;q<length;q++){
			System.out.print(o[q]+" ");
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] o){
		int length=o.length;
		int count=0;
		for(int i=0;i<length;i++){
			if(o[i]!=0){
				count++;
			}
		} 
		int[] newArray=new int[count];
		for(int i=0;i<length;i++){
			if(o[i]!=0){
				newArray[i]=o[i];
			}
		}
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
		int length1=array1.length;
		int length2=array2.length;
		int[] merge=new int[length1+length2];
		int index1=0;
		int index2=0;
		int index3=0;
		if(index1<length1&&index2<length2){
			if(array1[index1]<array2[index2]){
				merge[index3++]=array1[index1++];
			}else{
				merge[index3++]=array2[index2++];
			}
		}
		while(index1<length1){
			merge[index3++]=array1[index1++];
		}
		while(index2<length1){
			merge[index3++]=array2[index2++];
		}
		
		return  merge;
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
		if(size==0){
			return oldArray;
		}
		int length=oldArray.length;
		if(length==0){
			int[] temp=new int[size];
			for(int i=0;i<size;i++){
				temp[i]=0;
			}
			return temp;
		}
		int num=length+size;
		int[] grow=new int[num];
		for(int i=0;i<size;i++){
			grow[i]=oldArray[i];
		}
		for(int i=length;i<num;i++){
			grow[i]=0;
		}
		return grow;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		return null;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		return null;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if(max==0||max==1){
			return null;
		}
		int[] num=new int[max];
		for(int i=2;i<max;i++){
			for(int b=0;b<=(i/2);b++){
				int a=0;
				if(i%b==0){
					a=a+b;
				}
				if(i==a){
					num[i-2]=i;
				}
			}
		}
		return num;
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
		int length=array.length;
		String q="";
		for(int i=0;i<length;i++){
			if(i==length-1){
				q=q+array[i];
			}else{
				q=q+array[i]+seperator;
			}
		}
		return null;
	}
	
	public static void main(String args[]){
		int q=5/2;
		int w=7/2;
		System.out.println(w);
	}

}
