package array;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if (origin.length == 0) {
			return;
		}
		int n = origin.length - 1;
		int temp;
		for (int i = 0; i < n/2; i++) {
			temp = origin[i];
			origin[i] = origin[n - i];
			origin[n - i] = temp;
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
		if (oldArray.length == 0) {
			return null;
		}
		int n = oldArray.length;
		int zeros = 0;
		for (int i = 0; i < n - 1; i++) {
			if (oldArray[i] == 0) {
				zeros++;
			}
		}
		int[] result = new int[n - zeros];
		int j = 0;
		for (int i = 0; i < n - zeros - 1; i++) {
			while (oldArray[j] == 0) {
				j++;
			}
			result[i] = oldArray[j];
			j++;
		}
		return result;
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
	public int[] grow(int [] oldArray,  int size){
		return null;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max){
		if (max <= 1) {
			return new int[0];
		}
		int n = 1;
		int init = helpFibonacci(n);
		while (init < max) {
			n++;
			init = helpFibonacci(n);
		}
		int[] result = new int[n - 1];
		for (int i = 0; i < n - 1; i++) {
			result[i] = helpFibonacci(i + 1);
		}
		return result;
	}

	public static int helpFibonacci(int n) {
		if(n == 0)
			return 0;
		else if(n == 1)
			return 1;
		else
			return helpFibonacci(n -1 ) + helpFibonacci(n - 2);
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
	 * @param
	 * @return
	 */
	public String join(int[] array, String seperator){
		return null;
	}
	
	public static void main(String[] args) {
		int[] a = fibonacci(15);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
}
