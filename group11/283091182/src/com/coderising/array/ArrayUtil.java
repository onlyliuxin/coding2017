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
		if(origin==null||origin.length==0){
			throw new RuntimeException("invalid array argument!");
		}
		if(origin.length>1){
			int temp;
			for(int i=0;i<origin.length/2;i++){
				temp = origin[i];
				origin[i] = origin[origin.length-1-i];
				origin[origin.length-1-i]= temp;
			}
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
		if(oldArray==null || oldArray.length==0){
			return oldArray;
		}else{
			int[] tmp = new int[oldArray.length];
			int pos = 0;
			for(int i=0;i<oldArray.length;i++){
				if(oldArray[i]!=0){
					tmp[pos]=oldArray[i];
					pos++;
				}
			}
			int[] result = new int[pos];
			System.arraycopy(tmp, 0, result, 0, pos);
			return result;
		}
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		//Check array sorting
		checkIfArraySortedAsc(array1);
		checkIfArraySortedAsc(array2);
		//both are null then return null
		if(array1==null && array2==null){
			return null;
		}
		//one of them is empty array then return another
		//Scenario:array1 is empty
		if(array1.length==0){
			if(array2 == null){
				return array1;
			}else{
				return array2;
			}
		}else{
			//Scenario:array1 is not empty
			if(array2.length==0){
				return array1;
			//Scenario: both array1 and array2 are not empty
			}else{
				//Do merge in this case
				int[] array3 = new int[array1.length+array2.length];
				int pos1=0;
				int pos2=0;
				int pos3=0;
				while(pos1<array1.length || pos2<array2.length){
					if(pos1==array1.length){
						array3[pos3] = array2[pos2];
						pos2++;
						pos3++;
						continue;
					}
					if(pos2==array2.length){
						array3[pos3] = array1[pos1];
						pos1++;
						pos3++;
						continue;
					}
					if(array1[pos1]>array2[pos2]){
						array3[pos3]=array2[pos2];
						pos2++;
						pos3++;
					}else if(array1[pos1]<array2[pos2]){
						array3[pos3]=array1[pos1];
						pos1++;
						pos3++;
					}else{
						array3[pos3]=array1[pos1];
						pos1++;
						pos2++;
						pos3++;
					}
					
				}
				array3 = removeZero(array3);
				printArray("Merge result=",array3);
				return array3;
			}
		}
	}
	private void checkIfArraySortedAsc(int[] arr){
		if(arr==null||arr.length==0||arr.length==1){
			return;
		}else{
			for(int i=1;i<arr.length;i++){
				if(arr[i]<arr[i-1]){
					throw new RuntimeException("Array ["+join(arr,",")+"] is not sorted desc!");
				}
			}
			return;
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
	public int[] grow(int [] oldArray,  int size){
		int[] newArr = new int[oldArray.length+size];
		for(int i=0;i<oldArray.length;i++){
			newArr[i] = oldArray[i];
		}
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
		if(max==1){
			return new int[0];
		}else if(max==2){
			return new int[]{1,1};
		}else{
			int[] arr = new int[max];
			arr[0]=1;
			arr[1]=1;
			int pos=2;
			genFibonacci(arr,pos,max);
			
			int[] result = removeZero(arr);
			return result;
		}
	}
	private void genFibonacci(int[] arr,int pos,int max){
		int val = arr[pos-2]+arr[pos-1];
		if(val<max){
			arr[pos]=val;
			pos++;
			genFibonacci(arr,pos,max);
		}else{
			return;
		}
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int[] nonPrimeArr = new int[max];
		int tmp;
		//begin to calculate for nonPrime,start from 4 because 2 & 3 can not be calculated in this way
		for(int i=2;i<max;i++){
			for(int j=2;j<=i;j++){
				tmp = i*j;
				if(tmp<max){
					//Mark as nonPrime numbers
					nonPrimeArr[tmp]=1;
				}else{
					break;
				}
			}
		}
		int[] result = new int[max];
		int pos = 0;
		if(max>3){
			result[pos]=2;
			pos++;
		}
		if(max>4){
			result[pos]=3;
			pos++;
		}
		for(int i=4;i<nonPrimeArr.length;i++){
			//for those value==0, mean indicated as Primes,index is the prime number
			if(nonPrimeArr[i]==0){
				result[pos]=i;
				pos++;
			}
		}
		
		result = removeZero(result);
		return result;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		if(max<=1){
			throw new RuntimeException("Invalid Arguement :max number="+max);
		}else{
			int[] result = new int[max];
			int pos = 0;
			for(int i=2;i<=max;i++){
				int[] factors = getFactors(i);
				int val =0;
				//Sum of the factors
				for(int j:factors){
					val+=j;
				}
				//Check if the sum of factors equals to the number
				if(val==i){
					result[pos]=i;
					pos++;
				}
			}
			result = removeZero(result);
			return result;
		}
	}

	/**
	 * Get the factor of a integer
	 * @param num
	 * @return factors of this number as int array 
	 */
	private int[] getFactors(int num){
		int[] factors = new int[num];
		int pos = 0;
		for(int i=1;i<=num/2;i++){
			if(num%i==0){
				factors[pos]=i;
				pos++;
			}
		}
		return removeZero(factors);
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
		for(int i: array){
			if(sb.length()>0){
				sb.append(seperator);
			}
			sb.append(i);
		}
		return sb.toString();
	}
	
	private void printArray(String msg,int[] array){
		System.out.print(msg);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+",");
		}
		System.out.println();
	}
}
