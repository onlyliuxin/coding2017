	
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
			int size=origin.length-1;
			int[] arr=new int[origin.length];
			for(int i=0;i<origin.length;i++){
				arr[size]=origin[i];
				size--;
			}
			
			//输出结果
			for(int i=0;i<arr.length;i++){
				System.out.println(arr[i]);
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
			
			int[] arr=new int[oldArray.length];
			int size=0;
			for(int i=0;i<oldArray.length;i++){
				if(oldArray[i]!=0){
					arr[size]=oldArray[i];
					size++;
				}
			}
			
			int[] newArr=new int[size];//将不为零的数赋值给一个新的数组。
			for(int i=0;i<size;i++){
				newArr[i]=arr[i];
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
			
			int strLen1=array1.length;								//保存第一个数组长度
		    int strLen2=array2.length;								//保存第二个数组长度
		    array1= Arrays.copyOf(array1,strLen1+ strLen2);			//扩容
		    System.arraycopy(array2, 0, array1, strLen1,strLen2 );	//将第二个数组与第一个数组合并
		    System.out.println(Arrays.toString(array1));			//输出数组
			
		    //冒泡排序，并除去重复项。
		   for(int i=0;i<array1.length;i++){
			   for(int j=i+1;j<array1.length;j++){
				   if(array1[i]>array1[j]&&array1[i]!=0&&array1[j]!=0){
					   int temp;
					   temp=array1[i];
					   array1[i]=array1[j];
					   array1[j]=temp;
				   }
				   if(array1[i]==array1[j]&&array1[i]!=0&&array1[j]!=0){
					   array1[j]=0;
				   }
			   }
		   }
		   
		   System.out.println(Arrays.toString(array1));
		   
		   int[] array3=removeZero(array1);	//除零操作。
			return  array3;
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
			int[] newArr=new int[oldArray.length+size];
			for(int i=0;i<oldArray.length;i++){
				newArr[i]=oldArray[i];
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
			int first=1;
			int second=1;
			int[] arr=new int[2];
			if(max==1){
				arr=new int[0];
			}
			if(max==2){
				arr[0]=1;
				arr[1]=1;
			}
			if(max>2){
				arr[0]=1;
				arr[1]=1;
				while(second<max){
					
					int temp;
					temp=second;
					second=second+first;
					first=temp;
					if(second<max){
						arr=grow(arr,1);
						arr[arr.length-1]=second;	
					}
									
				}
			}
			
			
			return arr;
		}
		
		/**
		 * 返回小于给定最大值max的所有素数数组
		 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
		 * @param max
		 * @return
		 */
		public int[] getPrimes(int max){
			int[] arr=new int[max];
			int size=0;
			for(int i=2;i<max;i++){	//从2开始一次遍历到最大数max
				boolean flag=false;
				
				for(int j=2;j<i;j++){
					if(i%j==0){	//存在公约数，跳出。
						flag=true;
						break;
					}
				}
				if(!flag){//不存在公约数
					System.out.println(i);
					arr[size]=i;
					size++;
				}
			}
			int[] newArr=removeZero(arr);
			return newArr;
		}
		
		/**
		 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
		 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
		 * @param max
		 * @return
		 */
		public int[] getPerfectNumbers(int max){
			
			int[] arr=new int[10];
			int size=1;
			arr[0]=1;
			
			for(int i=1;i<max;i++){
				
				int sum=0;
				for(int j=1;j<i;j++){	//获取一个数除其本身全部的因数。
					if(i%j==0){			//为当前数的因数.
						sum=sum+j;		//将当前的因数相加。
					}
				}

				if(sum==i){
					arr[size]=i;
					size++;
				}
			
							
			}
			int[] a=removeZero(arr);	
			return a;
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
			String a="";
			for(int i=0;i<array.length;i++){
				if(i<(array.length-1)){
					a=a+""+array[i]+seperator;
				}
				else{
					a=a+""+array[i];
				}
			}
			return a;
		}
		
	
		
		
		
		
		
		
		//over
	}
