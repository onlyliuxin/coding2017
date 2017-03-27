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
		int first = 0;
		int last = origin.length - 1;
		
		while (first < last) {
			int temp;
			temp = origin[first];
			origin[first] = origin[last];
			origin[last] = temp;
			
			first++;
			last--;
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
		// count how many zeros 
		int zeroCount = 0;
		int len= oldArray.length;
		
		if (len==0) {
			return new int[]{};
		}
		
		for (int i=0; i<len; i++) {
			if (oldArray[i]==0) {
				zeroCount++;
			}
		}
		
		if (zeroCount == 0) {
			return oldArray;
		}
		
		// create a new array
		int newLen = len - zeroCount;
		int[] newArr = new int[newLen];
		
		// copy to the new array and return
		int k = 0;
		for (int j=0; j<len; j++) {
			if (oldArray[j]!=0) {
				newArr[k] = oldArray[j];
				k++;
			}
		}
		
		return newArr;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int len1 = array1.length;
		int len2 = array2.length;
		
		int newSize = len1+len2;
		int[] newArray = new int[newSize];
		
		int i = 0; // i for array1
		int j = 0; // j for array2
		int k = 0; // k for newArray
		while(i <=len1-1 && j<=len2-1) {
			if (array1[i] < array2[j]) {
				newArray[k++] = array1[i];
				i++;
			} else if (array1[i] == array2[j]){
				newArray[k++] = array1[i];
				newArray[k++] = 0;
				i++;
				j++;
			} else {
				newArray[k++] = array2[j];
				j++;
			}
		}
		
		while(i<len1) {
			newArray[k++] = array1[i++];
		}

		while(j<len2) {
			newArray[k++] = array2[j++];
		}
		
		// remove 0 after merge
		return this.removeZero(newArray);
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
		int len = oldArray.length;
		
		if (len ==0) {
			return new int[]{};
		}
		
		int newSize = len + size;
		int[] newArray = new int[newSize];
		
		for(int i=0; i<len; i++) {
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
		if (max ==1) {
			return new int[]{};
		}
		
		int[] arr = new int[max];
		arr[0] = 1;
		arr[1] = 1;
		
		int k = 2;
		do {
			arr[k] = arr[k-1] + arr[k-2];
		} while(arr[k++]<=max);
		
		// remove the last element since the condition exit 
		// when it is already passed the limit
		arr[k-1] = 0;
		
		return this.removeZero(arr);
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] primes = new int[max];
		for(int i=0; i<primes.length; i++) {
			primes[i] = i;
		}
		// we know 0 and 1 are not prime
		primes[0] = 0;
		primes[1] = 0;
		
		for (int i=2; i<primes.length; i++) {
			// if value is prime, all it's multiplier is prime and set to 0
			if(primes[i] !=0) {
				for(int j=2; j<primes.length; j++) {
					if(i*j < max) {
						primes[i*j] = 0;
					}
				}
			}
		}
		
		return this.removeZero(primes);
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if (max==1) {
			return new int[]{};
		}
		
		int[] newArr = new int[max];
		int k =0;
		int sum=0;
		for (int i=1; i<=max;i++) {
			for(int j=1; j<i;j++) {
				if(i%j ==0) {
					sum+=j;
				}
			}
			
			if(sum==i) {
				newArr[k++] = i;
			}
			
			sum=0;
		}
		
		return this.removeZero(newArr);
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
		if (array==null) {
			return null;
		}
		
		final int noOfItems = array.length;
		final StringBuilder buf = new StringBuilder(noOfItems * 16);
		for (int i =0; i<noOfItems; i++) {
			buf.append(Integer.toString(array[i]));
			if (i!=noOfItems-1 && seperator!=null) {
				buf.append(seperator);
			}
		}
		
		return buf.toString();
	}
	

}
