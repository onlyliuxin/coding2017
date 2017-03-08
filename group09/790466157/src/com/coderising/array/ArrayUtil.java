package com.coderising.array;
import java.util.*;

public class ArrayUtil {
	
	/**
	 * ����һ����������a , �Ը������ֵ�����û�
		���磺 a = [7, 9 , 30, 3]  ,   �û���Ϊ [3, 30, 9,7]
		���     a = [7, 9, 30, 3, 4] , �û���Ϊ [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int[] origin1 = {7,9,30,3};
		//��������Ԫ��
		for (int i = 0; i < origin1.length/2; i++){
		int tmp = origin1[i];
		origin1[i] = origin1[origin1.length-i-1];
		origin1[origin1.length-i-1] = tmp;
		}
		System.out.println(Arrays.toString(origin1));
	}
	
	/**
	 * ���������µ�һ�����飺   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * Ҫ������������ֵΪ0����ȥ��������Ϊ0��ֵ����һ���µ����飬���ɵ�������Ϊ��   
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
	 * ���������Ѿ�����õ��������飬 a1��a2 ,  ����һ���µ�����a3, ʹ��a3 ����a1��a2 ������Ԫ�أ� ������Ȼ�������
	 * ���� a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    �� a3 Ϊ[3,4,5,6,7,8]    , ע�⣺ �Ѿ��������ظ�
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
	 * ��һ���Ѿ��������ݵ����� oldArray������������չ�� ��չ��������ݴ�СΪoldArray.length + size
	 * ע�⣬�������Ԫ��������������Ҫ����
	 * ���� oldArray = [2,3,6] , size = 3,�򷵻ص�������Ϊ
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
	 * 쳲���������Ϊ��1��1��2��3��5��8��13��21......  ������һ�����ֵ�� ����С�ڸ�ֵ������
	 * ���磬 max = 15 , �򷵻ص�����Ӧ��Ϊ [1��1��2��3��5��8��13]
	 * max = 1, �򷵻ؿ����� []
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
	 * ����С�ڸ������ֵmax��������������
	 * ����max = 23, ���ص�����Ϊ[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
		public class primes {
			public void showPrimeNumber(int range){
	        boolean[] primes = this.sieve(range);
	        int number = 0 ;
	        if(primes != null){
	            int size = primes.length;
	            System.out.println("��Χ��"+range+"�ڵ����������У�");
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
	            System.out.println("�������ķ�Χrange�������0��");
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
	 * ��ν���������� ��ָ�����ǡ�õ�����������֮�ͣ�����6=1+2+3
	 * ����һ�����ֵmax�� ����һ�����飬 ��������С��max ����������
	 * @param max
	 * @return
	 */

		public static int[] PerfectNumbers(int max){
			int[] f = new int[max];
			int i,j,sum;    //sum�����������֮��        
	        for(i=0;i<max;i++)   //��1��numb���ڵ������γ���
	        {
	           sum = 0;          //��sum��ֵ��ͬʱҲ�Ƕ���һ�ε�ֵ���
	           for(j=1;j<=i/2;j++)   //��������
	           {
	               if(i%j==0)          // ���������
	               {
	                    sum+=j;        //�ѵ�ǰ�������ۼӵ�sum��   
	               }
	           }
	           if(sum==i)            //�ж��ǲ���������������֮�͵�������
	           {
	               f[i]=i;        //�����������
	           }
	        }
			return f;
		}
	
	/**
	 * ��seperator ������ array����������
	 * ����array= [3,8,9], seperator = "-"
	 * �򷵻�ֵΪ"3-8-9"
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