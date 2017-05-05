package net.coding.coderising.array;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int l = 0, r = origin.length - 1;
		while (l < r) {
			int tmp = origin[l];
			origin[l] = origin[r];
			origin[r] = tmp;
			l++;
			r--;
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
		int cntZero = 0;
		for (int i = 0; i < oldArray.length;i++) {
			if (oldArray[i] == 0) {
				cntZero ++;
			}
		}
		if (cntZero == 0) {
			return oldArray;
		}

		int[] newArray = new int[oldArray.length - cntZero];
		int j = 0;
		for (int i = 0; i < oldArray.length;i++) {
			if (oldArray[i] != 0) {
				newArray[j++] = oldArray[i];
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
		int[] result = new int[array1.length + array2.length];
		int l = 0;
		int r = 0;

		int cnt = 0;
		while (true) {
			if (l >= array1.length && r >= array2.length) {
				break;
			}
			if (l >= array1.length) {
				result[cnt++] = array2[r];
				r++;
			} else if (r >= array1.length) {
				result[cnt++] = array1[l];
				l++;
			} else {
				if (array1[l] < array2[r]) {
					result[cnt++] = array1[l];
					l++;
				} else {
					result[cnt++] = array2[r];
					r++;
				}
			}
		}

		return  result;
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
		System.arraycopy(oldArray, 0, result, 0, oldArray.length);
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
		int[] result = {};
		int a = 0;
		int b = 1;

		int cnt = 0;
		while (b < max) {
			result = grow(result, 1);
			result[cnt++] = b;
			int tmp = a + b;
			a = b;
			b = tmp;
		}

		return result;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[1,2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	private boolean isPrime(int n) {
		for (int i = 2; i <= Math.sqrt(n + 1.0); i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
	public int[] getPrimes(int max){
		int[] result = {};
		int cnt = 0;
		for (int i = 1; i < max; i++) {
			if (isPrime(i)) {
				result = grow(result, 1);
				result[cnt++] = i;
			}
		}
		return result;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	private boolean isPerfect(int n) {
		int total = 0;
		for (int i = 1; i < n; i++) {
			if (n % i == 0) {
				total += i;
			}
		}

		return total == n;
	}
	public int[] getPerfectNumbers(int max){
		int[] result = {};
		int cnt = 0;
		for (int i = 1; i < max; i++) {
			if (isPerfect(i)) {
				result = grow(result, 1);
				result[cnt++] = i;
			}
		}
		return result;
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
		for (int i = 0; i < array.length; i++) {
			if (sb.length() > 0) {
				sb.append(seperator);
			}
			sb.append(array[i]);
		}
		return sb.toString();
	}
	

}
