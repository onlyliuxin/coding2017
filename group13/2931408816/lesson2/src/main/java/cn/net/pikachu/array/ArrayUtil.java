package cn.net.pikachu.array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArrayUtil {
	
	/**
	 * 给定一个整形数组a , 对该数组的值进行置换
		例如： a = [7, 9 , 30, 3]  ,   置换后为 [3, 30, 9,7]
		如果     a = [7, 9, 30, 3, 4] , 置换后为 [4,3, 30 , 9,7]
	 * @param origin
	 * @return
	 */
	public void reverseArray(int[] origin){
		int[] reverse = new int[origin.length];
		for (int i = 0; i < origin.length; i++) {
			reverse[origin.length-1-i]=origin[i];
		}
        for (int i = 0; i < reverse.length; i++) {
            origin[i]=reverse[i];
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
		int count = oldArray.length;
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i] == 0){
				count -- ;
			}
		}
		int index = 0;
		int[] result = new int[count];
		for (int i = 0; i < oldArray.length; i++) {
			if (oldArray[i]!=0){
				result[index++]=oldArray[i];
			}
		}
		return result;
	}
	
	/**
	 * 给定两个已经排序好的整形数组， a1和a2 ,  创建一个新的数组a3, 使得a3 包含a1和a2 的所有元素， 并且仍然是有序的
	 * 例如 a1 = [3, 5, 7,8]   a2 = [4, 5, 6,7]    则 a3 为[3,4,5,6,7,8]    , 注意： 已经消除了重复
	 * @param array1
	 * @param array2
	 * @return
	 */
	
	public int[] merge(int[] array1, int[] array2){
		// 考虑到去重的问题　直接用数组无法确定大小　
        // 可悲的发现用了LinkedList并没有我想象中的那么好　最后还得手动复制一次
		LinkedList<Integer> list = new LinkedList<Integer>();
		int i = 0;
		int j = 0;
		int index = 0;
		int cur = array1[0]<array2[0]?array1[0]:array2[0];
		list.add(cur);
		while (i < array1.length && j < array2.length){
			if (array1[i] < array1[j]){
				if (array1[i]>cur){
					cur=array1[i];
					list.add(cur);
				}
				i++;
			}else {
				if (array2[j]>cur){
					cur=array2[j];
					list.add(cur);
				}
				j++;
			}
		}
		while (i < array1.length){
		    if (cur!= array1[i])
			list.add(array1[i++]);
		}
		while (j < array2.length){
            if (cur!= array2[j])
			list.add(array2[j++]);
		}
        int[] result = getIntsFromList(list);
        return result;
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
	    int[] result = new int[oldArray.length+size];
        for (int i = 0; i < oldArray.length; i++) {
            result[i] = oldArray[i];
        }
        return result;
	}
	
	/**
	 * 斐波那契数列为：1，1，2，3，5，8，13，21......  ，给定一个最大值， 返回小于该值的数列
	 * 例如， max = 15 , 则返回的数组应该为 [1，1，2，3，5，8，13]
	 * max = 1, 则返回空数组 []
	 * @param max
	 * @return
	 */
	public int[] fibonacci(int max){
	    if(max == 1){
	        return new int[]{};
        }
	    // 虽然之前被LinkedList坑到了　但是　我还是得用
        // 手动复制就手动复制吧
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.add(1);
        list.add(1);
        int a = 1;
        int b = 1;
        while (a+b<max){
            int c = a + b;
            a = b;
            b = c;
            list.add(c);
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
	}
	
	/**
	 * 返回小于给定最大值max的所有素数数组
	 * 例如max = 23, 返回的数组为[2,3,5,7,11,13,17,19]
	 * @param max
	 * @return
	 */
	public int[] getPrimes(int max){
	    // 首先确定如何判定是素数
        // 继续LinkedList　
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 2; i < max; i++) {
            if (isPrimes(i)){
                list.add(i);
            }
        }
        int[] r = getIntsFromList(list);
        return r;
	}

    /**
     * 这是一个我不得不写的函数　用LinkedList的心酸
     * @param list
     * @return
     */
    private int[] getIntsFromList(List<Integer> list) {
        int[] r = new int[list.size()];
        for (int i = 0; i < r.length; i++) {
            r[i]=list.get(i);
        }
        return r;
    }

    private boolean isPrimes(int num){
	    int end = (int) (Math.sqrt(num)+1);
        for (int i = 2; i < end; i++) {
            if (num % i == 0){
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
	public int[] getPerfectNumbers(int max){
	    // 与上面类似　首先判断是不是完数
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 2; i < max; i++) {
            if (isPerfectNumber(i)){
                list.add(i);
            }
        }
        return getIntsFromList(list);
	}
	private boolean isPerfectNumber(int num){
	    int sum = 1;
	    int end = (int) (Math.sqrt(num)+1);
        for (int i = 2; i < end; i++) {
            if (num % 2 == 0){
                sum+=2 + num/2;
            }
        }
        return sum == num;
    }
	/**
	 * 用seperator 把数组 array给连接起来
	 * 例如array= [3,8,9], seperator = "-"
	 * 则返回值为"3-8-9"
	 * @param array
	 * @param seperator
	 * @return
	 */
	public String join(int[] array, String seperator){
	    StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]).append(seperator);
        }
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
	}
	

}
