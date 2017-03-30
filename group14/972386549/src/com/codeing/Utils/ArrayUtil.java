package com.codeing.Utils;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int temp;
		int length = origin.length;
		for (int i = 0; i < (length/2); i ++ ){
			//将首尾位置交换
			temp = origin[i];
			origin[i] = origin[length - 1 - i];
			origin[length - 1 - i] = temp;
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
		for (int i = 0; i < oldArray.length; i ++){
			if (oldArray[i] == 0){
				System.arraycopy(oldArray, i + 1, oldArray, i, oldArray.length - 1 - i);
				removeZero(oldArray);
			}
		}
		return oldArray;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */	
	public int[] merge(int[] firstArr, int[] secondArr){
        int[] mergeArr = new int[firstArr.length + secondArr.length];
        if (firstArr[firstArr.length - 1] <= secondArr[0]){  //两个数组的收尾先比较，可以直接串连
            System.arraycopy(firstArr, 0, mergeArr, 0, firstArr.length);
            System.arraycopy(secondArr, 0, mergeArr, firstArr.length, secondArr.length);
        }else if (firstArr[0] >= secondArr[secondArr.length - 1]){
            System.arraycopy(secondArr, 0, mergeArr, 0, secondArr.length);
            System.arraycopy(firstArr, 0, mergeArr, secondArr.length, firstArr.length);
        }else {  //两个数组无法直接串连
            int i = 0;  //指向firstArr
            int j = 0;  //指向secondArr
            int count = 0;
            while ( (i < firstArr.length) && (j < secondArr.length) ){  //只要有一个结束了，就结束了循环
                if(firstArr[i] < secondArr[j]){  //较小的值填入合并后的数组，并且指针后移
                    mergeArr[count] = firstArr[i];
                    i ++;
                } else if (firstArr[i] > secondArr[j]) {
                    mergeArr[count] = secondArr[j];
                    j ++;
                } else {
                	mergeArr[count] = firstArr[i];
                    i ++;
                    j ++;
                }
                count ++;
            }
            if (i == firstArr.length ){  //将未结束的数组合并
                System.arraycopy(secondArr, j, mergeArr, count, secondArr.length - j);
            } else {
                System.arraycopy(firstArr, i, mergeArr, count, firstArr.length - j);
            }
        }
        return mergeArr;
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
	public int[] grow(int [] oldArr,  int size){
		int[] newArr = new int[oldArr.length + size];
		System.arraycopy(oldArr, 0, newArr, 0, oldArr.length);
		return newArr;
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
		String joinStr = new String();
		for (int i = 0; i < array.length - 1; i ++){
			joinStr += (array[i] + seperator);
		}
		return joinStr + array[array.length - 1];
	}
	
	public static void main(String[] args){

	}
	
}
