package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ArrayUtil {

    public static void main(String[] args) {
        int[] origin = new int[]{1, 2, 3, 4};
        System.out.println(Arrays.toString(getPrimes(23)));
    }

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果： a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
    /**
     * 1. 通过双指针实现反转
     * @param origin
     * @return
     */
	public static int[] reverseArray_1(int[] origin){
        int i = 0;
        int j = origin.length - 1;
        for (;i < j;) {
            int swap = origin[i];
            origin[i] = origin[j];
            origin[j] = swap;
            i++;
            j--;
        }

        return origin;
	}
	
	/**
	 * 2. 通过栈后进先出的特点实现反转
	 * @param origin
	 * @return
	 */
	public static int[] reverseArray_2(int[] origin) {
		Stack<Integer> stack = new Stack<>();
		
		for (int num : origin) {
			stack.push(num);
		}	
		for (int i = 0; i < origin.length; i++) {
			origin[i] = stack.pop();
		}
		return origin;
	}
	
	/**
	 * 3. 通过反序遍历实现反转
	 * @param origin
	 * @return
	 */
	public static int[] reverseArray_3(int[] origin) {
		int[] result = new int[origin.length];
		for (int i = origin.length - 1; i >= 0; i--) {
			result[origin.length - i - 1] = origin[i];
		}
		return result;
	}

	/**
	 * 现在有如下的一个数组：int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：
	 * {1,3,4,5,6,6,5,4,7,6,7,5}
	 * @param oldArray
	 * @return
	 */
	public static int[] removeZero_1(int[] oldArray){
		List<Integer> list = new ArrayList<>();
		for (int num : oldArray) {
			if (num != 0) {
				list.add(num);
			}
		}
		
		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}
	
	/**
	 * 通过双指针实现
	 * @param oldArray
	 * @return
	 */
	public static int[] removeZero_2(int[] oldArray) {
		int j = 0;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] != 0) {
				oldArray[j++] = oldArray[i];
			}
		}
		
		return Arrays.copyOf(oldArray, j);
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */

	public int[] merge(int[] array1, int[] array2){
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
	public static int[] grow(int [] oldArray,  int size){
		return Arrays.copyOf(oldArray, oldArray.length + size);
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		
		List<Integer> list = new ArrayList<>();
		int current = 0;
		for (int i = 0; i < max; i++) {
			if (i <= 1) {
				current = 1;
			} else {
				current = list.get(i - 1) + list.get(i - 2);
			}
			if (current >= max) break;
			list.add(current);
		}
		
		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * 写了一篇文章：http://bosschow.github.io/2017/03/05/get-prime-number-1/
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		List<Integer> list = new ArrayList<>();
		
		for (int i = 2; i < max; i++) {
			boolean isPrime = true;
			for (Integer primeNum : list) {
				if (primeNum <= Math.sqrt(i)) {
					if (i % primeNum == 0) {
						isPrime = false;
						break;
					}
				} else {
					break;
				}	
			}
			
			if (isPrime) {
				list.add(i);
			}
		}
		
		int[] result = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
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
	public static String join(int[] array, String seperator){
		StringBuilder stringBuilder = new StringBuilder();
		
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 1) {
				stringBuilder.append(array[i]);
			} else {
				stringBuilder.append(array[i] + seperator);
			}
		}
		
		return stringBuilder.toString();
	}


}
