package array;

import java.util.ArrayList;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin) {
		if (origin == null || origin.length <= 1) {
			return;
		}
		//第1和第length互换位置，第2和第length-1互换，以此类推
		for (int i = 0; i < origin.length / 2; i++) {
			int temp = origin[i];
			origin[i] = origin[origin.length - 1 - i];
			origin[origin.length - 1 - i] = temp;
		}
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	public int[] removeZero(int[] oldArray) {
		// 判断非法输入
		if (oldArray == null || oldArray.length == 0) {
			return null;
		}

		// 创建临时数组，长度与原oldArray一样长
		int[] temp = new int[oldArray.length];
		int tempCount = 0;
		for (int i = 0; i < oldArray.length; i++) {
			// 判断元素是0跳过
			if (oldArray[i] == 0) {
				continue;
			} else {// 非零放入临时数组，计数器加1
				temp[tempCount++] = oldArray[i];
			}
		}

		// 如果计数器与原数组长度一致，直接返回临时数组
		if (tempCount == oldArray.length) {
			return temp;
		} else {// 否则返回长度为tempCount的数组
			int[] retVal = new int[tempCount];
			System.arraycopy(temp, 0, retVal, 0, tempCount);
			return retVal;
		}
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	public int[] merge(int[] array1, int[] array2) {
		// 创建临时数组，长度为array1+array2的长度
		int[] temp = new int[array1.length + array2.length];
		int index = 0;// temp的下标
		int i = 0;// array1的下标
		int j = 0;// array2的下标
		int count = 0;// temp存放元素的计数器

		while (true) {

			if (i == array1.length) {// 当array1已经全部添加到temp，就把array2剩余的放入temp
				for (int l = j; l < array2.length; l++) {
					temp[index++] = array2[l];
					count++;
				}
				break;
			} else if (j == array2.length) {// 当array2已经全部添加到temp，就把array1剩余的放入temp
				for (int l = i; l < array1.length; l++) {
					temp[index++] = array1[l];
					count++;
				}
				break;
			}

			// 取array1第i和array2第j个元素比较。如果array1[i]的小于array2[j]，则array1[i]放入temp
			// 同时i++，使得下次为i+1和j个元素比较。
			// 如果array1第i和array2第j个元素相等。则array1[i]放入temp
			// 同时i++，j++，使得下次为i+1和j+1个元素比较。
			if (array1[i] < array2[j]) {
				temp[index++] = array1[i++];
				count++;
			} else if (array1[i] > array2[j]) {
				temp[index++] = array2[j++];
				count++;
			} else {
				temp[index++] = array1[i];
				i++;
				j++;
				count++;
			}
		}

		// 如果计数器和temp.length相等，直接返回
		if (count == temp.length) {
			return temp;
		} else {// 否则创建长度为count的新数组返回
			int[] retVal = new int[count];
			System.arraycopy(temp, 0, retVal, 0, count);
			return retVal;
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
	public int[] grow(int[] oldArray, int size) {
		// 参数校验
		if (size < 0) {
			throw new RuntimeException("非法参数");
		}
		if (size == 0) {
			return oldArray;
		}

		// 扩容
		int[] retVal = new int[oldArray.length + size];
		for (int i = 0; i < oldArray.length; i++) {
			retVal[i] = oldArray[i];
		}
		return retVal;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max) {
		// 0直接返回空数组
		if (max == 1) {
			return new int[0];
		}

		int retVal = 0;// 每一轮循环的斐波那契数
		int i = 1;// 获取第几个斐波那契数
		ArrayList<Integer> list = new ArrayList<Integer>();// 用于暂时保存斐波那契数
		while (retVal < max) {// 获取所有小于max的斐波那契数
			retVal = getFibonacci(i++);
			if (retVal < max) {
				list.add(retVal);
			} else {
				break;
			}
		}

		// 将arraylist转为array
		int[] retArray = new int[list.size()];
		for (int j = 0; j < list.size(); j++) {
			retArray[j] = list.get(j);
		}
		return retArray;
	}
	
	/**
	 * 循环获取第N个斐波那契数
	 * @param index
	 * @return
	 */
	private int getFibonacci(int index) {
		if (index < 1) {
			throw new RuntimeException("非法参数");
		}
		if (index == 1 || index == 2) {
			return 1;
		}

		int first = 1;
		int second = 1;
		int retVal = 0;
		for (int i = 3; i <= index; i++) {
			retVal = first + second;
			first = second;
			second = retVal;
		}
		return retVal;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max) {
		// 排除非法参数
		if (max < 0) {
			throw new RuntimeException("非法参数");
		}

		// 小于2没有素数
		if (max <= 1) {
			return new int[0];
		}

		ArrayList<Integer> list = new ArrayList<Integer>();
		// 取出每个数判断是不是素数，是就放入arraylist
		for (int i = 2; i < max; i++) {
			boolean primes = isPrimes(i);
			if (primes) {
				list.add(i);
			}
		}

		int[] retArray = new int[list.size()];
		// 将ArrayList转为数组返回
		for (int i = 0; i < list.size(); i++) {
			retArray[i] = list.get(i);
		}
		return retArray;
	}
	
	/**
	 * 判断输入的数字是不是素数
	 * @param num
	 * @return
	 */
	private boolean isPrimes(int num) {
		// 排除非法参数
		if (num <= 0) {
			throw new RuntimeException("非法参数");
		}

		// 小于2没有素数
		if (num == 1) {
			return false;
		}

		// 2是素数，但是2%2==0为true，所以单独列出
		if (num == 2) {
			return true;
		}

		// num依次从2到num的平方根取余数，如果余数为零说明不是素数
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
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
	public int[] getPerfectNumbers(int max) {
		if (max < 0) {
			throw new RuntimeException("非法参数");
		}
		if (max <= 2) {
			return new int[0];
		}

		// 获取小于max 的所有完数
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < max; i++) {
			if (isPerfectNumber(i)) {
				list.add(i);
			}
		}

		// 将ArrayList转为array返回
		int[] array = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			array[i] = list.get(i);
		}
		return array;
	}
	
	/**
	 * 判断输入的数是不是完美数
	 * @param num
	 * @return
	 */
	private boolean isPerfectNumber(int num) {
		if (num <= 0) {
			throw new RuntimeException("非法参数");
		}

		// 放置num的所有约数
		ArrayList<Integer> divisorList = new ArrayList<Integer>();
		for (int i = 1; i < num; i++) {
			if (num % i == 0) {
				divisorList.add(i);
			}
		}

		// 所有因数相加是否等于num，是就是完美数
		int count = 0;
		for (int i = 0; i < divisorList.size(); i++) {
			count += divisorList.get(i);
		}
		if (count == num) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public String join(int[] array, String seperator) {
		// 检查非法输入
		if (array == null) {
			return null;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (i == array.length - 1) {// 最后一个元素后面不拼接-
				sb.append(array[i]);
			} else {
				sb.append(array[i]);
				sb.append("-");
			}
		}
		return sb.toString();
	}
	

}