package com.coderising.array;
import java.util.*;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int[] origin1 = {7,9,30,3};
		//交换数组元素
		for (int i = 0; i < origin1.length/2; i++){
		int tmp = origin1[i];
		origin1[i] = origin1[origin1.length-i-1];
		origin1[origin1.length-i-1] = tmp;
		}
		System.out.println(Arrays.toString(origin1));
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		int[] oldArray1 = {1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5};
        int newArrayLength = getLength(oldArray1);
        int[] newArray = getNewArray(oldArray1, newArrayLength);
        print(oldArray1);
        print(newArray);
        return newArray;
    }
	
    public static int getLength(int[] array){
        int num = 0;
        for(int i = 0 ; i < array.length;i++){
            if(array[i] != 0){
                num++;
            }
        }
        return num;
    }

    public static int[]  getNewArray(int[] array,int num){
        int[] newArray = new int[num];
        int index = 0;
            for(int i = 0; i < array.length; i ++){
                if(array[i]!=0){
                    newArray[index] = array[i];
                    index++;
                }
            }
        return newArray;
    }
    public static void print(int [] array){
        for(int i : array){
            System.out.print(i+" ");
        }
        System.out.println();
    }

	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		int[] a={10,20,30,40,50};
		int[] b={10,20,60};
		int[] c = new int[a.length+b.length-cf(a,b)*2];
		int index = 0;
		for (int i=0;i<a.length;i++){
			if (!isExist(b,a[i])){
				c[index++] = a[i];
			}
			else{
	
			}
		}
		for (int i=0;i<b.length;i++){
			if (!isExist(a,b[i])){
				c[index++] = b[i];
			}
		}
		return c;
	}
	
    	public static int cf(int[] a,int [] b){
    		int num = 0;
    		for (int i=0;i<a.length;i++){
    			if (isExist(b,a[i])){
    				num++;
    			}
    		}
    		return num;
    	}
		
    	public static boolean isExist(int[] a,int s){
    		boolean d = false;
    		for (int i=0;i<a.length;i++){
    			if (s==a[i]){
    				d = true;
    			}
    		}
    		return d;
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
		int[] oldArray1 = {2,3,6};
		int size1 = 3;
		int aL = oldArray1.length;
		oldArray1 = Arrays.copyOf(oldArray1,aL + size1);
		System.arraycopy(oldArray1,0,oldArray1,aL + size1,aL);
		//System.out.println(Arrays.toString(oldArray1));
		return oldArray1;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
		public static int[] fibonacci(int max){
			int[] f = new int[max];
			f[0] = 1;
			f[1] = 1;
			for(int i =2; i < max; i++){
				f[i] = f[i-1] + f[i-2];
			}
			
			return f;
		}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
		public class primes {
			public void showPrimeNumber(int range){
	        boolean[] primes = this.sieve(range);
	        int number = 0 ;
	        if(primes != null){
	            int size = primes.length;
	            System.out.println("范围在"+range+"内的质数个数有：");
	            for(int i = 1 ; i< size ; i++){
	                if(primes[i]){
	                    System.out.print(i + " ");
	                  
	                if(++number %10 ==0){
	                    System.out.println();
	                }
	            }
	        }
	        System.out.println();
	    }
	}
	
	    private boolean[] sieve(int range){
	        if(range <= 0 ){
	            System.out.println("求质数的范围range必须大于0！");
	            return null;
	        }
	        boolean[] isPrime = new boolean[range + 1];
	        isPrime[1] = false ;
	        Arrays.fill(isPrime, 2,range+1,true);
	        int n = (int)Math.sqrt(range);
	        for(int i = 1 ; i <= n ; i++){
	            if(isPrime[i]){
	                for(int j = 2 * i ;j <= range ; j+=i){
	                    isPrime[j] = false;
	                     
	                }
	            }
	        }
	        return isPrime;
	    }
	}
	


	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */

		public static int[] PerfectNumbers(int max){
			int[] f = new int[max];
			int i,j,sum;    //sum用来存放因子之和        
	        for(i=0;i<max;i++)   //对1到numb以内的数依次尝试
	        {
	           sum = 0;          //给sum赋值，同时也是对上一次的值清空
	           for(j=1;j<=i/2;j++)   //查找因子
	           {
	               if(i%j==0)          // 如果是因子
	               {
	                    sum+=j;        //把当前的因子累加到sum中   
	               }
	           }
	           if(sum==i)            //判断是不是完数，即因子之和等于自身
	           {
	               f[i]=i;        //是完数，输出
	           }
	        }
			return f;
		}
	
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param s
	 * @return
	 */
	public static String join(Object[] array, char separator) {  
        if (array == null) {  
            return null;  
        }  
        int arraySize = array.length;  
        int bufSize = (arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0].toString().length()) + 1) * arraySize);  
        StringBuffer buf = new StringBuffer(bufSize);  
  
        for (int i = 0; i < arraySize; i++) {  
            if (i > 0) {  
                buf.append(separator);  
            }  
            if (array[i] != null) {  
                buf.append(array[i]);  
            }  
        }  
        return buf.toString();  
    } 
	

}