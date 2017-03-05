package day20170226.homework.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *  All method are static.
 * 
 *  Auth by RedKnife on on 2017-03-05 11:21:51
 */
public class ArrayUtil {
	public static void main(String[] args) {
	}
	
	//sence thread not safety, :(  
	//inefficient when multithread
	private static volatile List<Integer> container = new ArrayList<Integer>();
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin){
		if (origin == null || origin.length == 0){
			return ;
		}
		int dstIndex = origin.length - 1;
		for (int i = 0; i < (origin.length / 2) ; i++, dstIndex--){
			int temp = origin[i];
			origin[i] = origin[dstIndex];
			origin[dstIndex] = temp;
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public static int[] removeZero(int[] oldArray){
		if (oldArray == null || oldArray.length == 0){
			return oldArray;
		}
		container.clear();
		
		for (int i = 0; i < oldArray.length; i++){
			if(oldArray[i] != 0) {
				container.add(oldArray[i]);
			}
		}
		
		return listToArray(container);
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	//can sort and merge
	public static int[] merge(int[] array1, int[] array2){
		LinkedList<Integer> container = new LinkedList<Integer>();
		int searchLength = array1.length;
		
		for (int i = 0; i < array1.length; i++) {
			if(insertSort(container, array1[i], i)){
				searchLength--;
			}
		}
		
		for (int i = 0; i < array2.length; i++) {
			if(insertSort(container, array2[i], searchLength + i)){
				searchLength--;
			}
		}
		
		if (container.size() == 0){
			return array1;
		}
		
		return  listToArray(container);
	}
	
	public static boolean insertSort(List<Integer> dst, int src, int dstIndex){
		if (dstIndex > 0 && src == dst.get(dstIndex - 1)) {
			return true;
		}
		if (dstIndex > 0 && src <= dst.get(dstIndex - 1)) {
			dstIndex--;
			return insertSort(dst, src, dstIndex);
		} else {
			dst.add(dstIndex , src);
			return false;
		}
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
		if (size < oldArray.length || size == 0){
			throw new RuntimeException("not enough length");
		}
		return Arrays.copyOf(oldArray, oldArray.length + size);
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	//definition: Fa = 0, Fb = 1, Fn = Fa(n-1)+Fb(n-2)(n >= 2, n ∈ N*).
	public static int[] fibonacci(int max){
		if (max <= 1){
			return new int[]{};
		}
		container.clear();
		container.add(1);
		container.add(1);
		int fa = 1, fb = 1, fn = 0;  
		for (int i = 1; i < max; i ++) {
			fn = fa + fb;  
            fa = fb;  
            fb = fn; 
			if(fn > max){
				break;
			}
			container.add(fn);
		}
		return listToArray(container);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max){
		
		container.clear();
		
		outer : for (int i = 2; i < max; i++) {//the number to judge
			for (int j = 2; j <= Math.sqrt(i); j++) {//if is primes
				if (i%j == 0) {
					continue outer;
				}
			}
			container.add(i);
		}
		return listToArray(container);
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public static int[] getPerfectNumbers(int max){
		int number = 0;
		int factor = 0;
		int adder = 0;
		container.clear();
		for (number = 1; number < max; number++) {
			adder = 0;
			for (factor = 1; factor < number; factor++) {
				if(number % factor == 0) {
					adder += factor;
					if(adder == number && number/factor == 2) {
						container.add(number);
					}
				}
			}
		}
		
		return listToArray(container);
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
		if (array == null || array.length == 0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if ( i != array.length - 1) {
				sb.append(seperator);
			}
		}
		return sb.toString();
	}
	
	public static int[] listToArray(List<Integer> list){
		int[] result = new int[list.size()];
		for (int i = 0; i < result.length; i++){
			result[i] = list.get(i);
		}
		return result;
	}

}
