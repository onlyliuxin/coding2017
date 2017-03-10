package com.coding.week2;

public class ArrayUtil {
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static int[] reverseArray(int[] origin){
	int j=origin.length-1;
		for(int i=0;i<origin.length/2;i++){
			int temp = origin[i];
			origin[i] = origin[j];
			origin[j] = temp;
			j--;
		}
		return origin;
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray){
		int[] removeZero=new int[oldArray.length];
		int size = 0;
		for(int i=0;i<oldArray.length;i++ ){
			if(oldArray[i]!=0){
				removeZero[size]=oldArray[i] ;
				size++;
			}
		}
		
		
		return copyArray(removeZero, size);
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public static int[] merge(int[] array1, int[] array2){
		int[] array3 =copyArray(array2, array1.length+array2.length);
		int size = array2.length;
	
			for(int i=0;i<array1.length;i++){
				boolean insert = false;
				for(int j=0;j<size;j++){
					if(array1[i]<array3[j]){
						System.arraycopy(array3,j , array3,j+1 , size-j);
						array3[j]=array1[i];
						insert = true;
						size++;
						break;
					}else if(array1[i]==array3[j]){
						insert = true;
						break;
					}
				}
				if(!insert){
					array3[size]=array1[i];
					size++;
				}
			}
		
		return copyArray(array3, size);
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
		int[] newArray = new int[oldArray.length+size];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		return newArray;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		int[] nums = new int[max];
		int a = 1;
		int b = 1;
		int c = 0;
		int index = 2;
		nums[0] = 1;
		nums[1] = 1;
		 int temp =0;
		while(temp<max){
		    temp = nums[index-1]+nums[index-2];
		    if(temp<max){
		    	nums[index] = temp;
		    	index++;
		    }
			
			
		}
		return copyArray(nums, index);
		
	}

	private  static int[] copyArray(int[] nums, int size) {
		int[] newNums = new int[size];
		int length = size>=nums.length?nums.length:size;
		
		System.arraycopy(nums, 0, newNums, 0, length);
		return newNums;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	//这里为什么是开平方根
	public static  int[] getPrimes(int max){
		int i=2;
		int[] nums = new int[max];
		int index=0;
		while(i<max){
			boolean isSu = true;
			for(int j=1;j<Math.sqrt(i);j++){
				if(i%j==0){
					isSu=false;
				}
				
			}
			if(isSu){
				nums[index] = i;
			}
		}
		copyArray(nums, index+1);
		return null;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		int[] nums = new int[max];
		int sum = 0;
		int size=0;
		for(int i=0;i<=max;i++){
			sum+=(i+1);
			nums[i]=i+1;
			size++;
			if(sum==max){
				break;
			}
		}
		return copyArray(nums, size);
		
	}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public  static String join(int[] array, String seperator){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<array.length;i++){
			if(i!=array.length-1){
		     sb.append(array[i]+seperator);
			}else{
				sb.append(array[i]);
			}
			
		}
		return sb.toString();
	}
}
