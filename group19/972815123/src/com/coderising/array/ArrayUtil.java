package com.coderising.array;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if(origin == null || origin.length <= 1){
			return;
		}
		int tem = 0;
		for(int i = 0, len = origin.length; i < len/2; i ++){
			tem = origin[i];
			origin[i] = origin[len - i - 1];
			origin[len - i - 1] = tem;
		}
		System.out.println(join(origin, ","));
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int withoutZeroSize = 0;
		for(int i = 0, len = oldArray.length; i < len; i ++ ){
			if( oldArray[i] != 0){
				withoutZeroSize ++;
			}
		}
		int[] newArray = new int[withoutZeroSize];
		int point = 0;
		for(int i = 0 ,len = oldArray.length; i < len; i ++ ){
			if( oldArray[i] != 0){
				newArray[point] = oldArray[i];
				point ++;
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
		int point = 0;
		int point2 = 0, point1 = 0;
		int len1 = array1.length, len2 = array2.length;
		
		int[] result = new int[len1 + len2];
		while(point1 < len1 || point2 < len2){
			if(point1 < len1 && point2 < len2){
				if(array1[point1] <= array2[point2]){
					result[point] = array1[point1];
					point++;
					point1++;
				}else{
					if(result[point - 1] == array2[point2]){
						point2++;
					}else{
						result[point] = array2[point2];
						point++;
						point2++;
					}
				}
			}else{
				if(point1 < len1){
					result[point] = array1[point1];
					point++;
					point1++;
				}else{
					result[point] = array2[point2];
					point++;
					point2++;
				}
			}
		}
		result = removeZero(result);
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
		int[] newArray = new int[oldArray.length + size];
		for(int i = 0, len = oldArray.length; i < len; i ++){
			newArray[i] = oldArray[i];
		}
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
		int[] result = {1,1};
		if(max <= 1){
			return null;
		}else if(max == 2){
			return result;
		}else{
			result = grow(result, 10);
			int index = 1;
			while(true){
				index ++;
				if(result.length < index + 1){
					result = grow(result, 10);
				}
				result[index] = result[index -1] + result[index - 2];
				if(result[index] > max){
					break;
				}
			}
		}
		return removeZero(result);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] temArr = {2};
		if(max < 2){
			return null;
		}else if (max == 2){
			return temArr;
		}else{
			temArr = grow(temArr, 10);
			int index = 0;
			for(int i = 3;i < max; i ++){
				boolean flag = true;
				int iSqrt = (int) Math.sqrt(i);
				for(int j = 0; j < index + 1; j ++){
					if(iSqrt < temArr[j]){
						break;
					}
					if(i % temArr[j] == 0){
						flag = false;
						break;
					}
				}
				if(flag){
					index ++;
					if(temArr.length < index + 1){
						temArr = grow(temArr, 30);
					}
					temArr[index] = i;
				}
			}
		}
		return removeZero(temArr);
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int[] result = new int[10];
		int index = 0;
		if(max < 6){
			return null;
		}
		
		for (int n = 6; n <= max ; n ++){
			int[] allFactors = getAllFactors(n);
			int sum = 0;
			for(int i = 0, len = allFactors.length; i < len; i ++){
				sum += allFactors[i];
			}
			if(sum == n){
				if(result.length < index + 1){
					result = this.grow(result, 3);
				}
				result[index] = n;
				index ++;
			}

		}
		return removeZero(result);
	}
	
	public int[] getAllFactors(int n){
		int[] result = new int[n];
		int index = 0;
		for(int i = 1; i < n; i++){
			if(n % i == 0){
				result[index] = i;
				index ++;
			}
		}
		return removeZero(result);
	}
	
	//分解因式算法
	public int[] getPrimeFactors(int n){
		int[] allPrimes = getPrimes(n);
		int[] result = new int[allPrimes.length];
		int index = 1;
		result[0] = 1;
		for(int i = 0, len = allPrimes.length; i < len; i ++){
			int devide = n;
			while(devide % allPrimes[i] == 0){
				devide = devide / allPrimes[i];
				result[index] = allPrimes[i];
				index ++;
			}
		}
		return this.removeZero(result);
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
		StringBuffer sb = new StringBuffer();
		for(int i = 0, len = array.length; i < len; i ++){
			if(i != 0){
				sb.append(seperator);
			}
			sb.append(array[i]);
		}
		return sb.toString();
	}
	

}
