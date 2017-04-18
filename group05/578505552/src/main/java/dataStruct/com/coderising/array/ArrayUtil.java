package dataStruct.com.coderising.array;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){

		if(origin == null){
			return;
		}
		int length = origin.length;
		int i = 0;
		int j = length - 1;
		while (i < j){
			int tmp = origin[i];
			origin[i] = origin[j];
			origin[j] = tmp;
			i++;
			j--;
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
		int[] newArray = new int[length];
		int j = 0;
		for (int i = 0; i < length; i++) {
			if (oldArray[i] != 0){
				newArray[j++] = oldArray[i];
			}
		}
		int[] res = new int[j];
		System.arraycopy(newArray, 0, res, 0, j);
		return res;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){

		if(array1 == null && array2 == null){
			return new int[0];
		}

		if (array1 == null){
			return array2;
		}

		if (array2 == null){
			return array1;
		}

		int length1 = array1.length;
		int length2 = array2.length;
		int i = 0;
		int j = 0;

		int[] res = new int[length1 + length2];
		int k = 0;
		while (i < length1 || j < length2){
			int next = Integer.MIN_VALUE;
			if (i < length1 && j < length2){
				if (array1[i] == array2[j]){
					next = array1[i];
					i++;
					j++;
				} else if (array1[i] < array2[j]){
					next = array1[i++];
				} else {
					next = array2[j++];
				}
			} else if (i < length1){
				next = array1[i++];
			} else {
				next = array2[j++];
			}

			if (k == 0){
				res[k++] = next;
			} else if (next > res[k-1]){
				res[k++]  = next;
			}
		}

		int[] merged = new int[k];
		System.arraycopy(res, 0, merged, 0, k);
		return  merged;
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
		if (size < 0){
			throw new IllegalArgumentException("illegal size");
		}
		int newLength = oldArray.length + size;
		int[] newArray = new int[newLength];
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
	public int[] fibonacci(int max){

		if (max <= 1){
			return new int[0];
		}

		int[] res = new int[max];
		int i = 1;
		int j = 1;
		int k = 0;
		res[k++] = 1;
		res[k++] = 1;

		int tmp = i + j;
		while (tmp < max){
			res[k++] = tmp;
			i = j;
			j = tmp;
			tmp = i + j;
		}

		int[] result = new int[k];
		System.arraycopy(res, 0, result, 0, k);
		return result;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		if (max < 3){
			return new int[0];
		}
		int[] res = new int[max];
		int k = 0;
		for (int i = 2; i < max; i++) {
			if (isPrime(i)){
				res[k++] = i;
			}
		}
		int[] result = new int[k];
		System.arraycopy(res, 0, result, 0, k);
		return result;
	}

	private boolean isPrime(int num){

		if (num < 1){
			return false;
		}
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0){
				return false;
			}

		}
		return true;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){

		if (max < 0){
			return new int[0];
		}
		int[] res = new int[max];
		int k = 0;
		for (int i = 0; i < max; i++) {
			if (isPerfectNumbers(i)){
				res[k++] = i;
			}
		}
		int[] result = new int[k];
		System.arraycopy(res, 0, result, 0, k);
		return result;
	}

	private boolean isPerfectNumbers(int num){

		return num == getFactorSum(num);
	}

	private int getFactorSum(int num){
		if (num == 0 || num == 1){
			return -1;
		}
		int sum = 0;
		for (int i = 1; i <= num / 2; i++) {
			if (num % i == 0){
				sum += i;
			}
		}
		return sum;
	}
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param separator
	 * @return
	 */
	public String join(int[] array, String separator){

		if (array == null || array.length <= 0){
			return "";
		}

		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < array.length - 1; i++) {
			stringBuffer.append(String.valueOf(array[i])).append(separator);
		}
		stringBuffer.append(String.valueOf(array[array.length-1]));
		return stringBuffer.toString();
	}

	public static void main(String[] args) {
		ArrayUtil arrayUtil = new ArrayUtil();
		System.out.println("-------------------------");
	}
}
