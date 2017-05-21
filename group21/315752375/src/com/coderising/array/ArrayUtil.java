package com.coderising.array;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换 例如： a = [7, 9 , 30, 3] , 置换后为 [3, 30, 9,7] 如果 a =
	 * [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * 
	 * @param origin
	 * @return
	 */
	public static void reverseArray(int[] origin) {
		int length = origin.length;
		int tmp = 0;
		for (int i = 0; i < length / 2; i++) {
			tmp = origin[i];
			origin[i] = origin[length - i - 1];
			origin[length - i - 1] = tmp;
		}
	}

	/**
	 * 现在有如下的一个数组： int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为： {1,3,4,5,6,6,5,4,7,6,7,5}
	 * 
	 * @param oldArray
	 * @return
	 */

	public static int[] removeZero(int[] oldArray) {
		int last = 0;// 璁板綍鏈�悗涓�釜鍏冪礌鐨勪綅缃�
		int length = oldArray.length;
		int zero = 0;// 璁板綍鏁扮粍涓涓�釜0鐨勪綅缃�
		int nonzero = 1;// 璁板綍zero鍚庨潰绗竴涓潪闆剁殑浣嶇疆
		while (zero < length - 1) {
			if (oldArray[zero] == 0) {
				// System.out.println("zero:" + zero);
				// nonzero = zero + 1;
				while (nonzero < length) {
					if (oldArray[nonzero] != 0)
						break;
					nonzero++;
				}
				// System.out.println("nonzero:" + nonzero);
				if (nonzero >= length)
					break;
				oldArray[zero] = oldArray[nonzero];
				oldArray[nonzero] = 0;
				last = zero;
				// System.out.println(last);
			} else
				zero++;
		}
		int[] target = new int[last + 1];
		// System.out.println(target.length);
		System.arraycopy(oldArray, 0, target, 0, last + 1);
		return target;
	}

	/**
	 * 给定两个已经排序好的整形数组， a1和a2 , 创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的 例如 a1 =
	 * [3, 5, 7,8] a2 = [4, 5, 6,7] 则 a3 为[3,4,5,6,7,8] , 注意： 已经消除了重复
	 * 
	 * @param array1
	 * @param array2
	 * @return
	 */

	public static int[] merge(int[] array1, int[] array2) {
		int index1 = 0;// 鎸囧悜涓嬩竴涓厓绱�
		int index2 = 0;// 鎸囧悜涓嬩竴涓厓绱�
		int len1 = array1.length;
		int len2 = array2.length;
		int[] target = new int[len1 + len2];
		int index = 0;// 鎸囧悜涓嬩竴涓┖浣嶇疆
		int min = 0;
		while (index1 < len1 && index2 < len2) {
			if (array1[index1] < array2[index2]) {
				target[index++] = array1[index1++];
			} else {
				target[index++] = array2[index2++];
			}
		}
		if (index1 >= len1)
			System.arraycopy(array2, index2, target, index, len2 - index2);
		else
			System.arraycopy(array1, index1, target, index, len1 - index1);
		return target;
	}
	/**
	 * 把一个已经存满数据的数组 oldArray的容量进行扩展， 扩展后的新数据大小为oldArray.length + size
	 * 注意，老数组的元素在新数组中需要保持 例如 oldArray = [2,3,6] , size = 3,则返回的新数组为
	 * [2,3,6,0,0,0]
	 * 
	 * @param oldArray
	 * @param size
	 * @return
	 */
	public static int[] grow(int[] oldArray, int size) {
		if (size < 0)
			throw new IllegalArgumentException();
		System.out.println("asdfwefaw");
		int len = oldArray.length;
		int[] target = new int[len + size];
		System.arraycopy(oldArray, 0, target, 0, len);
		return target;
	}

	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21...... ，给定一个最大值， 返回小于该值的数列 例如， max = 15 ,
	 * 则返回的数组应该为 [1，1，2，3，5，8，13] max = 1, 则返回空数组 []
	 * 
	 * @param max
	 * @return
	 */
	public static int[] fibonacci(int max) {
		long first = 0;// 璁板綍绗竴涓暟
		int[] target = new int[max];
		int count = 0;
		long i = 1;// 璁板綍鍚庝竴涓暟
		long max1 = max;
		while (i < max1) {
			long tmp = i;
			target[count++] = (int) i;
			i = i + first;
			first = tmp;
		}
		int[] answer = new int[count];
		System.arraycopy(target, 0, answer, 0, count);
		return answer;
	}

	/**
	 * 返回小于给定最大值max的所有素数数组 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * 
	 * @param max
	 * @return
	 */
	public static int[] getPrimes(int max) {// 绱犳暟绛涢�娉�
		boolean[] primes = new boolean[max];
		int[] target = new int[max / 2];
		int count = 0;
		for (int i = 1; i < max; i += 2) {// 鍏ㄩ儴鍋舵暟榛樿false锛屽鏁拌涓簍rue
			primes[i] = true;
		}
		primes[2] = true;// 2涓哄伓鏁颁絾鏄槸绱犳暟
		for (int i = 3; i < (int) Math.sqrt(max); i += 2) {// 0锛�闈炵礌鏁伴潪鍚堟暟锛岃繕鏈�鏄礌鏁帮紝鎵�互浠�寮�
			if (primes[i])
				for (int j = i + i; j < max; j += i) {// 灏唅鐨勬暣鍊嶆暟涓嶆槸绱犳暟锛岃涓篺alse
					primes[j] = false;
				}
		}
		for (int i = 2; i < max; i++) {// 鏈�悗鎵�湁涓嬫爣浠�寮�鐨則rue涓虹礌鏁�
			if (primes[i])
				target[count++] = i;
		}
		int[] answer = new int[count];
		System.arraycopy(target, 0, answer, 0, count);
		return answer;
	}

	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * 
	 * @param max
	 * @return
	 */

	public static int[] getPerfectNumbers(int max) {
		long start = System.currentTimeMillis();
		long sum = 0;
		long max1=max;
		int[] array = new int[50];
		int count = 0;
		int count1 = 5;
		for (long i = 1; i < max1;) {
			try {
				// System.out.println("i:" + i + " sum:" + sum);
				if (i % 10 != 6 && i % 10 != 8) {
					continue;
				} else if (i != 6) {
					// System.out.println(i + " " + i % 3 + " " + i % 9);
					if (i % 3 != 1 || i % 9 != 1) {
						continue;
					}
				}
				sum = 1;// 1宸蹭竴瀹氫负鍥犲瓙
//				 System.out.print("鏁�+i+"鐨勫洜瀛愶細");
//				 System.out.print(1+" ");
				for (long j = 2; j < Math.sqrt(i); j++) {
					if (i % j == 0) {
						sum += j;
						sum += i / j;
//						 System.out.print(j+" "+i/j+" ");
					}

				}
				if (Math.abs(Math.sqrt(i) - (int) (Math.sqrt(i))) == 0) {
					sum += (int) Math.sqrt(i);
				}
				// System.out.println();
				 System.out.println("i:" + i + " sum:" + sum);
				if (sum == i)
					array[count++] = (int)i;
			} finally {
				if (i < 28)
					i++;
				else {
					i = i + (int) Math.pow(count1, 3);
					count1 += 2;
					 System.out.println(i);
				}
			}
		}

		int[] answer = new int[count];
		System.arraycopy(array, 0, answer, 0, count);
		long cost = System.currentTimeMillis() - start;
		System.out.println("cost time: " + cost + "ms");

		return answer;
	}

	/**
	 * 用seperator 把数组 array给连接起来 例如array= [3,8,9], seperator = "-" 则返回值为"3-8-9"
	 * 
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(int[] array, String seperator) {

		int len = array.length;
		String answer = "";
		for (int i = 0; i < len - 1; i++) {
			answer += array[i];
			answer += seperator;
		}
		answer += array[len - 1];

		return answer;
	}

}
