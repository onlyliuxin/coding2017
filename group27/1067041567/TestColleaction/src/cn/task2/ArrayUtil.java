package cn.task2;

import org.junit.Test;

public class ArrayUtil {

	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		enSure(origin);
		int len = origin.length-1;
		for(int k=0;k<origin.length/2;k++){
			int temp = origin[k];
			origin[k] = origin[len-k];
			origin[len-k] = temp;
		}
		
/*		该方法不会改变实参
 * 		int[] temp = new int[origin.length];
		int len = origin.length-1;
		for(int k=0;k<origin.length;k++){
			temp[len-k] = origin[k];
		}
		origin = temp;
		System.out.println("转换后origin：1-->"+origin);
 */
	}
	
	/**
	 * 现在有如下的一个数组：   int oldArr[]={1,3,4,5,0,0,6,6,0,5,4,7,6,7,0,5}   
	 * 要求将以上数组中值为0的项去掉，将不为0的值存入一个新的数组，生成的新数组为：   
	 * {1,3,4,5,6,6,5,4,7,6,7,5}  
	 * @param oldArray
	 * @return
	 */
	
	public int[] removeZero(int[] oldArray){
		enSure(oldArray);
		int len = 0;
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]!=0){
				len++;
			}
		}
		if(len==0){
			try {
				throw new Exception("转换的数组不为空，但有且只有一个为0的元素！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int[] temp = new int[len];
		int k = 0;
		for(int i=0;i<oldArray.length;i++){
			if(oldArray[i]!=0){
				temp[k++] = oldArray[i];
			}
		}
		return temp;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		enSure(array1);
		enSure(array2);
		int[] temp = new int[array1.length+array2.length];
		System.arraycopy(array1, 0, temp, 0, array1.length);
		System.arraycopy(array2, 0, temp, array1.length, array2.length);
		//冒泡排序
		for(int i=0;i<temp.length-1;i++){
			for(int j=0;j<temp.length-i-1;j++){
				if(temp[j] > temp[j+1]){
					int arr = temp[j];
					temp[j] = temp[j+1];
					temp[j+1] = arr;
				}
			}
		}
		for(int i=0;i<temp.length;i++){
			System.out.print("temp["+i+"] = "+temp[i]+"  ");
		}
		System.out.println();
		//查找不重复元素的个数
		int len = 0;
		for(int i=0;i<temp.length-1;i++){
			if(temp[i] != temp[i+1]){
				len++;
				continue;
			}
		}
		if(temp[temp.length-2] != temp[temp.length-1])
			len++;
		
		//赋值给新的元素
		int[] temp2 = new int[len];
		int k = 0;
		for(int i=0;i<temp.length-1;i++){
			if(temp[i] != temp[i+1]){
				temp2[k++] = temp[i];
			}
		}
		if(temp[temp.length-2] != temp[temp.length-1])
			temp2[temp2.length-1] = temp[temp.length-1];
/*		for(int i=0;i<temp2.length;i++){
			System.out.print("temp2["+i+"] = "+temp2[i]+"  ");
		}*/
		
		return  temp2;
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
		int len = oldArray.length+size;
		int[] temp = new int[len];
		System.arraycopy(oldArray, 0, temp, 0, oldArray.length);
		for(int i=0;i<len;i++){
			System.out.println("new["+i+"] = "+temp[i]);
		}
		return temp;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
		if(max < 1){
			try {
				throw new Exception("max小于1，找不出合适的数组！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(max == 1){
			return null;
		}
		int k = 0;
		for(int i=1;i<max;i++){
			if(count(i)<max){
				k++;
			}else{
				break;
			}
		}
		int[] temp = new int[k];
		for(int i=0;i<k;i++){
			temp[i] = count(i+1);
		}
		return temp;
	}
	
	public int count(int num){
		if(num <= 0){
			try {
				throw new Exception("num不可以小于0！");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(num ==1 || num ==2){
			return 1;
		}else{
			return count(num-1)+count(num-2);
		}
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
		boolean isprime = true;  
        int len = 0;
		
        for(int i=2;i<max;i++){  
        	for(int j=2;j<i;j++){  
        		if(i%j == 0){  
        			isprime = false;  
                    break;  
                }  
            }  
            if(isprime){
            	len++;  
           }
            isprime = true;  
        }
        
        int[] temp = new int[len];
        int k = 0 ;
        for(int i=2;i<max;i++){  
        	for(int j=2;j<i;j++){  
        		if(i%j == 0){  
        			isprime = false;  
                    break;  
                }  
            }  
            if(isprime){
            	temp[k++] = i;
           }
            isprime = true;  
        }
		return temp;
	}
	
	/**
	 * 所谓“完数”， 是指这个数恰好等于它的因子之和，例如6=1+2+3
	 * 给定一个最大值max， 返回一个数组， 数组中是小于max 的所有完数
	 * @param max
	 * @return
	 */
	public int[] getPerfectNumbers(int max){
		int k = 0;
		for(int i=1;i<=max;i++){
			int sum=0;
            for (int j = 1; j < i; j++){
            	if(i%j==0){
            		sum+=j;
            	}
            }
            if(i==sum){
                k++;
            }
        }
		
		int[] arr = new int[k];
		int len = 0;
		for(int i=1;i<=max;i++){
			int sum=0;
            for (int j = 1; j < i; j++){
            	if(i%j==0){
            		sum+=j;
            	}
            }
            if(i==sum){
                arr[len++] = i;
            }
        }
		return arr;
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
		for(int i=0;i<array.length-1;i++){
			sb.append(array[i]+""+seperator);
		}
		sb.append(array[array.length-1]);
		return sb.toString();
	}
	
	public void enSure(int[] arr){
		if(arr==null){
			throw new NullPointerException("该数组为空！");
		}
	}
}
