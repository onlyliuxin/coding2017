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
		int size=origin.length;
		if(size>1){
				for(int i=0;i<size/2;i++){
					int temp=origin[i];			//记录origin[i]的值
					origin[i]=origin[size-1-i];
					origin[size-1-i]=temp;
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
		int size=oldArray.length;
		int zero=0;	//记录0的数目
		for(int i=0;i<size;i++){
			if(oldArray[i]==0){
				zero++;
			}
		}
		if(zero==0){
			return oldArray;
		}
		else{
			int [] newArray=new int[size-zero];	//新建一个数组
			int now=0;//记录新数组的下标
			for(int i=0;i<size;i++){
				if(oldArray[i]!=0){
					newArray[now]=oldArray[i];
					now++;
				}
			}
			return newArray;
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
		int size1=array1.length;
		int size2=array2.length;
		int newArray[];
		if(size1==0&&size2==0){
			return  null;
		}
		else{
			newArray=new int[size1+size2];
		}
		if(size1>0)
			System.arraycopy(array1, 0, newArray, 0, size1);
		if(size2>0)
			System.arraycopy(array2,0, newArray,  size1, size2);
		for(int i=0;i<newArray.length;i++){
			int min=newArray[i];
			for(int j=i+1;j<newArray.length;j++){	// [2, 4, 2, 5, 9, 6, 3, 1, 10, 8]
				if(min==newArray[j]){				//去除重复数
					System.arraycopy(newArray,j+1, newArray,  j, newArray.length-j-1);
					int [] a=new int[newArray.length-1];
					System.arraycopy(newArray,0, a,  0, newArray.length-1);
					newArray=a;
				}
				if(min>newArray[j]){
					int temp=min;
					min=newArray[j];
					newArray[i]=min;
					newArray[j]=temp;
				}
			}
		}
		return  newArray;
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
			a=new int[10];
			int record=2;
			do{
				a[0]=1;
				a[1]=1;
				if(a.length>record)
					a[record]=a[record-2]+a[record-1];
				else{
					a=grow(a,3);
					a[record]=a[record-2]+a[record-1];
				}
				record++;
			}while(a[record-1]<max);
			int[] b=new int[record-1];
			System.arraycopy(a, 0, b, 0, record-1);
			a=b;
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
		int[] primes=new int[10];
		int record=0;
		while(true){
			boolean isPrime=true;//是否是质数的标志
			if(max<=n){
				break;
			}
			int jude=(int) Math.sqrt(n);
			for(int i=jude;i>1;i--){
				if(n%i==0){
					 isPrime=false;
				}
			}
			if(isPrime){
				if(record<primes.length)
					primes[record]=n;
				else{
					primes=grow(primes,5);
					primes[record]=n;
				}
				record++;
			}
			n++;
		}
		if(record<10){			//如果小于初始数组要去除0
			primes=removeZero(primes);
		}
		if(record<primes.length-1){
			int[] newArray=new int [record];
			System.arraycopy(primes, 0, newArray, 0, record);
			primes=newArray;
		}
		return primes;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int min=6;
		int[] perfect=new int[3];
		int record=0;
		if(max>=min){
			while(true){
				boolean isPerfect=false;//是否是完数的标志
				if(max<=min){
					break;
				}
				int n=(int) Math.sqrt(min);
				int count=0;
				for(int i=n;i>=1;i--){
					if(min%i==0){
						count=count+i;
						int b=min/i;
						if(b!=min)
							count=count+b;
					}
				}
				if(count==min){
					isPerfect=true;
				}
				if(isPerfect){
					if(record<perfect.length)
						perfect[record]=min;
					else{
						perfect=grow(perfect,1);
						perfect[record]=min;
					}
					record++;
				}
				min++;
			}
			if(record<3){			//如果小于初始数组要去除0
				perfect=removeZero(perfect);
			}
			if(record<perfect.length-1){
				int[] newArray=new int [record];
				System.arraycopy(perfect, 0, newArray, 0, record);
				perfect=newArray;
			}
		}
		return perfect;
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
