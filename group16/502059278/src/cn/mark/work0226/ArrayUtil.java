package cn.mark.work0226;

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
		int[] target = new int[origin.length];//声明置换后数组
		int temp = target.length - 1;//记录置换后下标位置
		for( int i = 0; i < origin.length; i++ ){
			target[temp] = origin[i];
			temp--;
		}
		System.out.println("置换前："+Arrays.toString(origin));
		System.out.println("置换后："+Arrays.toString(target));
		
		

	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int[] target = new int[1];
		boolean flag = true;
		for( int i = 0; i < oldArray.length; i++ ){
			if ( oldArray[i] == 0 ){ // 跳过值为0的元素
				continue;
			}
			
			if ( flag ){
				//首位赋值无需扩容
				target[target.length-1] = oldArray[i];
				flag = false;
			}else{
				//确定值不是0才能进入扩容步骤
				target = Arrays.copyOf(target, target.length+1);
				target[target.length-1] = oldArray[i];	
			}
			
		
			
		}
		System.out.println("去0前："+Arrays.toString(oldArray));
		System.out.println("去0后："+Arrays.toString(target));
		return target;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		//1.去重
		int[] array3 = Arrays.copyOf(array1, array1.length);
		for( int i = 0; i < array1.length; i++ ){
			for( int j = 0; j < array2.length ; j++ ){
				if ( array1[i] == array2[j] ){
					
				}
			}
			
			
			
			
		}
		
		System.out.println(Arrays.toString(array1));
		System.out.println(Arrays.toString(array2));
		//2.合并
		
		//3.排序
		
		
		return  null;
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
		int[] newArray = Arrays.copyOf(oldArray, oldArray.length+size);
		System.out.println("扩容后："+Arrays.toString(newArray));
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
		StringBuilder sb = new StringBuilder();
		for( int i = 0; i < array.length; i++ ){
			if ( i == 0 ){
				sb.append(array[i]);
			}else{
				sb.append(seperator+array[i]);
			}
		}
		return sb.toString();
	}
	

	public static void main(String[] args) {
		int[] a1 =new int[]{3,8},   a2 = new int[]{4, 5, 6,7};
		System.out.println(new ArrayUtil().join(a1, "*"));
		
	}
}
