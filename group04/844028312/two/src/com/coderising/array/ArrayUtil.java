package com.coderising.array;

import java.util.Arrays;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		if(origin==null){
			return;
		}
		int size=origin.length;
		for(int i=0;i<size/2;i++){
				int temp=origin[i];			//记录origin[i]的值
				origin[i]=origin[size-1-i];
				origin[size-1-i]=temp;
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
		int size=oldArray.length;
		int zero=0;	//记录0的数目
		int []array=new int[size];
		for(int i=0;i<size;i++){
			if(oldArray[i]!=0){
				array[i]=oldArray[i];
			}
			else{
				zero++;
			}
		}
		if(zero==0){
			return oldArray;
		}	
		return Arrays.copyOf(array, zero);
		
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		if(array1==null){
			return array2;
		}
		if(array2==null){
			return array1;
		}
		int size1=array1.length;
		int size2=array2.length;
		int newArray[]=new int[size1+size2];
		int count=0;
		int i=0;int j=0;
		while(i<size1 && j<size2){
			 if(array1[i]>array2[j]){
				 newArray[count++]=array2[j++];
				
			 }
			 if(array1[i]<array2[j]){
				 newArray[count++]=array1[i++];
				
			 }
			 if(array1[i]==array2[j]){
				 newArray[count++]=array1[i++];
				 j++;
			 }
		}
		while(i==size1&&j<size2){
			 newArray[count++]=array2[j++];
		}
		while(j==size1&&i<size1){
			 newArray[count++]=array1[i++];
		}
		return  Arrays.copyOf(newArray, count);
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
		int [] newArray=new int[oldArray.length+size];
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
		int [] a = null;
		if(max>2){
			a=new int[max];
			int record=2;
			do{
				a[0]=1;
				a[1]=1;
				a[record]=a[record-2]+a[record-1];
				record++;
			}while(a[record-1]<max);
			a=Arrays.copyOf(a, record-1);
		}
		else if(max==2){
			a=new int[2];
			a[0]=1;
			a[1]=1;
		}
		return a;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		int n=2;//初始素数大小
		int[] primes=new int[max];
		int record=0;
		if(max>=2){
			while(n<max){
				int jude=(int) Math.sqrt(n);
				boolean isPrime=true;//是否是质数的标志
				for(int i=jude;i>1;i--){
					if(n%i==0){
						isPrime=false;
						break;
					}
					
				}
				if(isPrime){
					primes[record]=n;
					record++;
				}
				n++;
			}
		}
		
		return Arrays.copyOf(primes, record);
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int min=6;
		int[] perfect=new int[10];
		int record=0;
		if(max>=min){
			while(min<max){
				int sum=0;
				for(int i=min-1;i>0;i--){
					if(min%i==0){
						sum=sum+i;
					}
				}
				if(sum==min){
					perfect[record]=min;
					record++;
				}
				min++;
				
			}
		}
		return Arrays.copyOf(perfect, record);
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
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<array.length;i++){
			if(i==array.length-1)
				sb.append(array[i]);
			
			else
				sb.append(array[i]+seperator);
			
		}
		return sb.toString();
	}
	

}
